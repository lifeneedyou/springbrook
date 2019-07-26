package com.brook.utils;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: xuequan
 * @Date: 2019/3/4 20:51
 * @Description:
 */
public class GitHubTool {

    private static Logger logger = LoggerFactory.getLogger(GitHubTool.class);

    private final static String url = "https://github.com/vicrab/vicrab_log_openresty.git";
    private final static String username = "developer@vicrab.com";
    private final static String password = "ZhuangXulin2003";
    private final static String localRepositoryPath = "/opt/vicrab_log_openresty";

    /**
     * 增加项目配置
     *
     * @param projectType
     * @param projectId
     * @return
     */
    public static synchronized boolean addProjectConf(String projectType, String projectId) {
        projectType = projectType.toLowerCase();
        // Clone or pull project code
        File file = new File(localRepositoryPath);
        if (!file.exists()) {
            cloneRepository();
        } else {
            pullRepository();
        }
        // 新增项目配置
        boolean addNginxConfig = addNginxConf(projectType, projectId);
        if (addNginxConfig) {
            // 将新生成的文件复制历史文件
            if (moveNginxConf(projectType)) {
                //  push 文件
                if (pushRepository()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除项目配置
     *
     * @param projectType
     * @param projectId
     * @return
     */
    public static synchronized boolean deleteProjectConf(String projectType, String projectId) {
        projectType = projectType.toLowerCase();
        // Clone or pull project code
        File file = new File(localRepositoryPath);
        if (!file.exists()) {
            cloneRepository();
        } else {
            pullRepository();
        }
        // 新增项目配置
        boolean deleteNginxConfStatus = deleteNginxConf(projectType, projectId);
        // 复制生成的文件
        if (deleteNginxConfStatus) {
            // 将新生成的文件复制历史文件
            if (moveNginxConf(projectType)) {
                //  push 文件
                if (pushRepository()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Clone 配置版本信息
     *
     * @return
     */
    private static boolean cloneRepository() {
        try {
            File file = new File(localRepositoryPath);
            CredentialsProvider user = new UsernamePasswordCredentialsProvider(username, password);
            CloneCommand clone = Git.cloneRepository();
            clone.setDirectory(file);
            clone.setURI(url);
            clone.setCredentialsProvider(user);
            Git git = clone.call();
            logger.debug("tag:{}", git.tag());
            return true;
        } catch (Exception e) {
            logger.error("cloneRepository error.{}", e);
            return false;
        }
    }

    /**
     * 从 github 垃取数据
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    private static boolean pullRepository() {
        File file = new File(localRepositoryPath);
        File gitFile = new File(localRepositoryPath + "/.git");
        if (gitFile.exists()) {
            try {
                Git gitResult = Git.open(file);
                PullCommand command = gitResult.pull();
                command.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password)).setRebase(true).call();
            } catch (IOException | GitAPIException e) {
                logger.error("pullRepository error.{}", e);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 新增项目配置
     *
     * @param projectType
     * @param projectId
     * @return
     */
    private static boolean addNginxConf(String projectType, String projectId) {
        // 文件路径和名称
        String fileName = "/nginx/conf/nginx_" + projectType.toLowerCase() + ".conf";
        // 读取以及验证文件中是否存在这个项目
        boolean projectInfoInFile = verifyProjectInfoInConf(fileName, projectId);
        if (!projectInfoInFile) {
            // 新增项目配置
            File file = new File(localRepositoryPath + fileName);
            boolean content405Position = false;
            boolean content405BracketsPosition = false;
            try (FileReader fReader = new FileReader(file)) {
                BufferedReader in = new BufferedReader(fReader);
                FileOutputStream out = new FileOutputStream(file + ".new");
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.contains("logs/405.log")) {
                        content405Position = true;
                    }
                    if (content405Position) {
                        if (line.contains("}")) {
                            content405BracketsPosition = true;
                        }
                    }
                    out.write((line + "\n").getBytes());
                    if (content405BracketsPosition) {
                        out.write("\n".getBytes());
                        out.write(("\tlocation ^~ /api/" + projectId + "/store {" + "\n").getBytes());
                        out.write(("\t\taccess_by_lua_file /usr/local/openresty/nginx/conf/request_compress.lua;" + "\n").getBytes());
                        out.write(("\t\troot html;" + "\n").getBytes());
                        out.write(("\t\tproxy_method GET;" + "\n").getBytes());
                        out.write(("\t\taccess_log  logs/" + projectType + "_sdk_post.log  main;" + "\n").getBytes());
                        out.write(("\t\techo_read_request_body;" + "\n").getBytes());
                        out.write(("\t}" + "\n").getBytes());
                        content405BracketsPosition = false;
                        content405Position = false;
                    }
                }
            } catch (IOException e) {
                logger.error("addNginxConf error.{}", e);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 删除项目配置
     *
     * @param projectType
     * @param projectId
     * @return
     */
    private static boolean deleteNginxConf(String projectType, String projectId) {
        // 文件路径和名称
        String fileName = "/nginx/conf/nginx_" + projectType.toLowerCase() + ".conf";
        // 读取以及验证文件中是否存在这个项目
        boolean projectInfoInFile = verifyProjectInfoInConf(fileName, projectId);
        if (projectInfoInFile) {
            // 删除项目配置
            boolean contentPosition = false;
            File file = new File(localRepositoryPath + fileName);
            try (FileReader fReader = new FileReader(file)) {
                BufferedReader in = new BufferedReader(fReader);
                FileOutputStream out = new FileOutputStream(file + ".new");
                String line;
                int deleteNumber = 0;
                while ((line = in.readLine()) != null) {
                    if (line.contains("/api/" + projectId + "/store")) {
                        contentPosition = true;
                    }
                    if (contentPosition) {
                        deleteNumber++;
                    }
                    if (deleteNumber > 7 || deleteNumber == 0) {
                        out.write((line + "\n").getBytes());
                    }
                }
                return true;
            } catch (IOException e) {
                logger.error("deleteNginxConf error.{}", e);
                return false;
            }
        }
        return false;
    }

    /**
     * 移动文件
     *
     * @param projectType 项目类型
     * @return
     */
    private static boolean moveNginxConf(String projectType) {
        // 文件路径和名称
        String oldFileName = "/nginx/conf/nginx_" + projectType.toLowerCase() + ".conf";
        // 验证文件是否更新过
        String oldFileMd5 = getFileMD5(new File(localRepositoryPath + oldFileName));
        // 获取本地的配置文件
        String newFileMd5 = getFileMD5(new File(localRepositoryPath + oldFileName + ".new"));
        if (oldFileMd5 != null && !oldFileMd5.equalsIgnoreCase(newFileMd5)) {
            try {
                copyFile(new File(localRepositoryPath + oldFileName + ".new"), new File(localRepositoryPath + oldFileName));
            } catch (IOException e) {
                return false;
            }
        }
        deleteFile(new File(localRepositoryPath + oldFileName + ".new"));
        return true;
    }

    /**
     * 复制文件
     *
     * @param source 原始文件
     * @param dest   复制保存的目标文件
     * @throws IOException
     */
    private static void copyFile(File source, File dest)
            throws IOException {
        InputStream input = new FileInputStream(source);
        OutputStream output = new FileOutputStream(dest);
        byte[] buf = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buf)) > 0) {
            output.write(buf, 0, bytesRead);
        }
        input.close();
        output.close();
    }

    /**
     * 删除文件
     *
     * @param source
     */
    private static void deleteFile(File source) {
        if (source.exists()) {
            source.delete();
        }
    }

    /**
     * 验证文件中是否存在项目的配置信息
     *
     * @param fileName
     * @param projectId
     * @return true： 存在，false：不存在
     */
    private static boolean verifyProjectInfoInConf(String fileName, String projectId) {
        // 读取以及验证文件中是否存在这个项目
        File file = new File(localRepositoryPath + fileName);
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] bytes = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            if (sb.toString().contains("/api/" + projectId + "/store")) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    /**
     * 提交文件到 GitHub
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    private static boolean pushRepository() {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try {
            Repository repository = builder.setGitDir(new File(localRepositoryPath + "/.git"))
                    .readEnvironment()
                    .findGitDir()
                    .build();
            Git git = new Git(repository);
            AddCommand add = git.add();
            add.addFilepattern(".").call(); //git add .
            CommitCommand commit = git.commit();
            commit.setCommitter("vicrab developer", "developer@vicrab.com");
            commit.setAuthor("vicrab developer", "developer@vicrab.com");
            commit.setAll(true);
            commit.setMessage("update nginx conf").call();//git commit -m "use jgit"
            PushCommand push = git.push();
            push.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            push.call();
        } catch (GitAPIException | IOException e) {
            return false;
        }
        return true;
    }


    /**
     * 获取文件 MD5 值
     *
     * @param file
     * @return
     */
    private static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }


    public static void main(String[] args) {
//        GitHubTool.addProjectConf("java", "20006");
//        GitHubTool.addProjectConf("java", "20007");
//        GitHubTool.deleteProjectConf("java","20006");
    }

}

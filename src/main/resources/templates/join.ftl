<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="Vicrab可帮助您识别和调试错误，并为您提升平台系统的洞察力，以决定何时修复或回滚。">
    <meta name="Keywords" content="vicrab,识别错误,调试错误，提升平台系统的洞察力，洞察力, 修复bug,回滚,bug,自动检查问题,app,java,ios">
    <meta itemprop="image" content="http://cdn.wenyayun.com/images/vicrab_web/logo.png">
    <title>VICRAB - 帮助您识别和调试错误，并为您提升平台系统的洞察力，以决定何时修复或回滚</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-size: 16px;
        }

        .components-container {
            width: 100%;
            overflow: hidden;
            background: #fff;
            border: 1px solid #e0e0e0;
            margin-top: 5%;
        }

        .email-header {
            padding-bottom: 15px;
        }

        .email-container {
            margin: auto;
            width: 100%;
            overflow: hidden;
        }

        .join-main-info {
            padding: 40px 15px 0 35px;
            font-size: 18px;
            line-height: 28px;
        }

        .join-main-info p {
            padding: 0;
            margin: 0
        }

        .join-main-info h5 {
            margin: 0px 0 15px;
            font-size: 22px;
            font-weight: normal;
        }

        .email-content {
            width: 50%;
            margin: auto;
            border-radius: 4px;
        }

        .logo-container {
            display: block;
            width: 140px;
            overflow: hidden;
            text-align: left;
            margin: 0 30px;
        }

        .email-header-center {
            width: 100%;
            overflow: hidden;
            border-bottom: 1px solid #c9c9c9;
        }

        .logo-img {
            display: block;
            float: left;
            width: 50px;
        }

        .logo-sologan {
            display: block;
            float: left;
            line-height: 50px;
            margin-left: 10px;
            font-weight: bold;
            color: #383838;
            font-size: 20px;
        }

        .confirm-btn {
            width: 150px;
            height: 50px;
            font-size: 18px;
            background: #009eff;
            border: 0;
            margin: 0;
            padding: 0;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }

        #footer-email {
            width: 100%;
            overflow: hidden;
            color: #fff;
            text-align: center;
            margin-top: 25px
        }

        #footer-email .container {
            background: #303133;
            padding-bottom: 25px;
        }

        .email-foot-ul {
            width: 300px;
            overflow: hidden;
            list-style: none;
            padding: 0;
            margin: auto;
            text-align: center;
        }

        .email-foot-ul li {
            float: left;
            padding: 0 10px;
            border-right: 1px solid #737373;
        }

        .email-foot-ul li a {
            color: #fff;
            text-decoration: none;
        }

        .footer-compay {
            background: #262626;
            padding: 10px 0;
            margin: 0;
        }

        #footer-email .container h4 {
            padding: 20px 0 0;
            margin: 0
        }

        #footer-email .container p {
            font-size: 14px;
        }

        .email-home {
            border-top: 1px solid #f1efef;
            text-align: right;
            padding: 30px 0 0;
        }

        .email-home a {
            color: #ababab;
            text-decoration: none;
        }

        @media screen and (max-width: 768px) {
            .email-content {
                width: 95%;
            }

            .join-main-info {
                padding: 15px;
                font-size: 14px;
            }

            .confirm-btn {
                width: 130px;
                height: 40px;
                font-size: 16px;
            }
        }

        .confirm-btn-a {
            display: block;
            text-decoration: none;
            text-align: center;
            line-height: 50px;
            width: 150px;
            height: 50px;
            font-size: 18px;
            background: #009eff;
            border: 0;
            margin: 0;
            padding: 0;
            color: #fff;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<div class="email-content">
    <div class="components-container">
        <div class="email-container">
            <div class="email-header">
                <div class="email-header-center">
                    <a href="https://www.vicrab.com" class="logo-container join-header-logo">
                        <img src="http://cdn.wenyayun.com/images/vicrab_web/logo.png" class="logo-img">
                        <span class="logo-sologan">
                                VICRAB
                            </span>
                    </a>
                </div>
                <div class="join-main-info">
                    <h5>
                        你被邀请加入Vicrab
                    </h5>
                    <p>
                        您在
                        <span class="organize_name">${(organizeName)!""}</span>
                        的队友正在使用Vicrab来跟踪和调试系统错误
                    </p>
                    <div style="margin: 20px 0;">
                        <a class="confirm-btn-a" target="_blank" id="confirm"
                           href="https://www.vicrab.com/accept?organize=${(organizeKey)!''}&email=${(mail)!''}">加入组织</a>
                    </div>
                    <p style="    padding-bottom: 20px;">
                        如果您想了解更多信息,请查看 <a href="https://www.vicrab.com" style="color: #3f9dff;">Vicrab</a>。
                    </p>
                    <div class="email-home">
                        <a href="https://www.vicrab.com">
                            首页
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


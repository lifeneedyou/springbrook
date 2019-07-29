package com.brook.config;
/*
 * Copyright (c) 2017-present, Pumpkin Movie, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Pumpkin Movie.
 *
 * As with any software that integrates with the pumpkin movie platform, your use of
 * this software is subject to the Pumpkin Movie Developer Principles and Policies
 * [http://developers.vcinema.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Johnson on 2017/10/09.
 */
@Component
@ConfigurationProperties(prefix = "vicrab")
public class EnvConfig {

    private String env;

    /**
     * Require this method to auto set property from application.yml
     * refers to doras:env property
     *
     * @param env
     */
    public void setEnv(String env) {
        this.env = env;
    }

    public Env getEnv() {
        if (StringUtils.isEmpty(env)) {
            throw new IllegalStateException("property spring.profiles.active requires but not assigned");
        }
        switch (env) {
            case "dev":
                return Env.DEV;
            case "test":
                return Env.TEST;
            case "prod":
                return Env.PROD;
            case "travis":
                return EnvConfig.Env.TRAVIS;
            default:
                throw new IllegalArgumentException("unrecognized spring.profiles.active property '" + env + "'");
        }
    }


    public enum Env {
        DEV,
        TEST,
        PROD,
        TRAVIS
    }
}

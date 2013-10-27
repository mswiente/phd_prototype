/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jswiente.phd.prototype.batchroute;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;
import org.springframework.beans.factory.annotation.Value;

/**
 * Mediation Batch Camel Route
 */
public class MediationBatchRoute extends RouteBuilder {

	@Value("${batch.source.endpoint}")
	private String sourceEndpoint;
	
	@Value("${batch.destination.endpoint}")
	private String destinationEndpoint;
	
	@Value("${batch.dir.output}")
	private String batchDirOutput;
	
	@Value("${batch.job.id}")
	private String jobId;
	
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.setApplicationContextUri("META-INF/spring/camel-context.xml");
        main.run(args);
    }

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        from(sourceEndpoint)
        .to("spring-batch:" + jobId + "?jobLauncherRef=jobLauncher").id("MEDIATION_BATCH");
        
        from("file:" + batchDirOutput)
        .to(destinationEndpoint).id("FTP");
    }
}

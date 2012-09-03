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
package com.jswiente.phd.prototype.BillingRouter;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.Main;

/**
 * Billing Camel Router
 */
public class BillingRouteBuilder extends RouteBuilder {

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		Main.main(args);
	}

	/**
	 * Let's configure the Camel routing rules using Java code...
	 */
	public void configure() {
        
		errorHandler(deadLetterChannel("activemq:queue:BILLING.ERRORS"));
		
        from("activemq:queue:BILLING.USAGE_EVENTS")
        .to("log:com.jswiente.phd?showAll=true&multiline=true")
        .unmarshal("myJaxb")
        .to("cxf:bean:mediationEndpoint?dataFormat=POJO")
        .to("log:com.jswiente.phd?showAll=true&multiline=true")
        .process(new MediationResponseProcessor())
        .to("cxf:bean:ratingEndpoint?dataFormat=POJO")
        .process(new RatingResponseProcessor())
        .to("file:target/ratingResponse")
        .to("jpa:Costedevent?usePersist=true");
    }
}

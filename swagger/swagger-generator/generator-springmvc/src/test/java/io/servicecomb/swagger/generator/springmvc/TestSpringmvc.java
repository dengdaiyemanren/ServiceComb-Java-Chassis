/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.swagger.generator.springmvc;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.swagger.generator.core.CompositeSwaggerGeneratorContext;
import io.servicecomb.swagger.generator.core.SwaggerGeneratorContext;
import io.servicecomb.swagger.generator.core.unittest.UnitTestSwaggerUtils;
import io.servicecomb.swagger.generator.springmvc.SpringmvcSwaggerGeneratorContext;

public class TestSpringmvc {
    SwaggerGeneratorContext context = new SpringmvcSwaggerGeneratorContext();

    @Test
    public void testMultiDefaultPath() {
        UnitTestSwaggerUtils.testException(
                "Only allowed one default path. io.servicecomb.swagger.generator.springmvc.MultiDefaultPath:p2",
                context,
                MultiDefaultPath.class);
    }

    @Test
    public void testResponseEntity() throws Exception {
        UnitTestSwaggerUtils.testSwagger("schemas/responseEntity.yaml", context, Echo.class, "testResponseEntity");
    }

    @Test
    public void testEmptyPath() throws Exception {
        UnitTestSwaggerUtils.testSwagger("schemas/emptyPath.yaml", context, Echo.class, "emptyPath");
    }

    @Test
    public void testEcho() throws Exception {
        UnitTestSwaggerUtils.testSwagger("schemas/echo.yaml", context, Echo.class, "echo");
    }

    @Test
    public void testDefaultParam() throws Exception {
        UnitTestSwaggerUtils.testSwagger("schemas/defaultParam.yaml", context, Echo.class, "defaultParam");
    }

    @Test
    public void testInheritHttpMethod() throws Exception {
        UnitTestSwaggerUtils.testSwagger("schemas/inheritHttpMethod.yaml", context, Echo.class, "inheritHttpMethod");
    }

    @Test
    public void testClassMethodNoPath() throws Exception {
        UnitTestSwaggerUtils.testException(
                "generate operation swagger failed, io.servicecomb.swagger.generator.springmvc.ClassMethodNoPath:noPath",
                "Path must not both be empty in class and method",
                context,
                ClassMethodNoPath.class);
    }

    @Test
    public void testClassMethodNoHttpMetod() throws Exception {
        UnitTestSwaggerUtils.testException(
                "generate operation swagger failed, io.servicecomb.swagger.generator.springmvc.ClassMethodNoHttpMethod:noHttpMethod",
                "HttpMethod must not both be empty in class and method",
                context,
                ClassMethodNoHttpMethod.class);
    }

    @Test
    public void testMethodMultiHttpMethod() throws Exception {
        UnitTestSwaggerUtils.testException(
                "generate operation swagger failed, io.servicecomb.swagger.generator.springmvc.Echo:multiHttpMethod",
                "not allowed multi http method for io.servicecomb.swagger.generator.springmvc.Echo:multiHttpMethod",
                context,
                Echo.class,
                "multiHttpMethod");
    }

    @Test
    public void testClassMultiHttpMethod() throws Exception {
        UnitTestSwaggerUtils.testException(
                "not allowed multi http method for io.servicecomb.swagger.generator.springmvc.ClassMultiHttpMethod",
                context,
                ClassMultiHttpMethod.class);
    }

    @Test
    public void testMethodMultiPath() throws Exception {
        UnitTestSwaggerUtils.testException(
                "generate operation swagger failed, io.servicecomb.swagger.generator.springmvc.Echo:multiPath",
                "not allowed multi path for io.servicecomb.swagger.generator.springmvc.Echo:multiPath",
                context,
                Echo.class,
                "multiPath");
    }

    @Test
    public void testClassMultiPath() throws Exception {
        UnitTestSwaggerUtils.testException(
                "not support multi path for io.servicecomb.swagger.generator.springmvc.ClassMultiPath",
                context,
                ClassMultiPath.class);
    }

    @Test
    public void testComposite() {
        CompositeSwaggerGeneratorContext composite = new CompositeSwaggerGeneratorContext();
        SwaggerGeneratorContext context = composite.selectContext(Echo.class);

        Assert.assertEquals(SpringmvcSwaggerGeneratorContext.class, context.getClass());
    }
}

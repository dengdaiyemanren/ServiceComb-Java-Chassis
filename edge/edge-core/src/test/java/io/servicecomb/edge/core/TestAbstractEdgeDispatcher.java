/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.edge.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class TestAbstractEdgeDispatcher {
  class AbstractEdgeDispatcherForTest extends AbstractEdgeDispatcher {
    @Override
    public int getOrder() {
      return 0;
    }

    @Override
    public void init(Router router) {
    }
  }


  @Test
  public void onFailure(@Mocked RoutingContext context) {
    Map<String, Object> map = new HashMap<>();
    HttpServerResponse response = new MockUp<HttpServerResponse>() {
      @Mock
      HttpServerResponse setStatusCode(int statusCode) {
        map.put("code", statusCode);
        return null;
      }

      @Mock
      HttpServerResponse setStatusMessage(String statusMessage) {
        map.put("msg", statusMessage);
        return null;
      }
    }.getMockInstance();

    new Expectations() {
      {
        context.failure();
        returns(new Error("failed"), null);
        context.response();
        result = response;
      }
    };

    AbstractEdgeDispatcherForTest dispatcher = new AbstractEdgeDispatcherForTest();
    dispatcher.onFailure(context);

    Assert.assertEquals(502, map.get("code"));
    Assert.assertEquals("Bad Gateway", map.get("msg"));
  }
}

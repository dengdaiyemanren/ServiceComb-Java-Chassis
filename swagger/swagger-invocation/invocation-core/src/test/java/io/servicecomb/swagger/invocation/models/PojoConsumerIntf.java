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

package io.servicecomb.swagger.invocation.models;

import java.util.List;

import io.servicecomb.swagger.invocation.context.InvocationContext;

public interface PojoConsumerIntf {
    int testSimple(int a, int b, int c);

    Person testObject(Person user);

    String testSimpleAndObject(String prefix, Person user);

    String testContext(InvocationContext context, String name);

    byte[] testBytes(byte[] bytes);

    String[] testArrayArray(String[] s);

    List<String> testArrayList(String[] s);

    String[] testListArray(List<String> s);

    List<String> testListList(List<String> s);

    Person[] testObjectArrayArray(Person[] s);

    List<Person> testObjectArrayList(Person[] s);

    Person[] testObjectListArray(List<Person> s);

    List<Person> testObjectListList(List<Person> s);
}

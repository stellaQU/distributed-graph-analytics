/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.soteradefense.dga.graphx.wcc

import com.soteradefense.dga.graphx.harness.Harness
import org.apache.spark.SparkContext
import org.apache.spark.graphx.Graph

import scala.reflect.ClassTag

/**
 * Abstract class for running weakly connected components.
 */
abstract class AbstractWCCRunner extends Harness with Serializable {

  /**
   * The return type of R is S.
   */
  override type R = S

  /**
   * Runs our implementation of weakly connected components.
   * @param sc The current spark context.
   * @param graph A Graph object with an optional edge weight.
   * @tparam VD ClassTag for the vertex data type.
   * @return The type of R
   */
  def run[VD: ClassTag](sc: SparkContext, graph: Graph[VD, Long]): R = {
    val wccCore = new WeaklyConnectionComponentsCore
    save(wccCore.runWeaklyConnectedComponents(graph))
  }

  /**
   * Runs the graphx implementation of weakly connected components.
   * @param graph A graph
   * @tparam VD The type of the vertex attribute.
   * @return The type of S.
   */
  def runGraphXImplementation[VD: ClassTag](graph: Graph[VD, Long]): S = {
    val wccCore = new WeaklyConnectionComponentsCore
    save(wccCore.runWeaklyConnectedComponentsGraphX(graph))
  }
}

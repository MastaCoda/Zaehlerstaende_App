/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-06-26 16:27:34 UTC)
 * on 2013-07-26 at 22:06:35 UTC 
 * Modify at your own risk.
 */

package de.roman.meter.userendpoint.model;

/**
 * Model definition for MeterCollection.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class MeterCollection extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Meter> items;

  static {
    // hack to force ProGuard to consider Meter used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(Meter.class);
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Meter> getItems() {
    return items;
  }

  /**
   * @param items items or {@code null} for none
   */
  public MeterCollection setItems(java.util.List<Meter> items) {
    this.items = items;
    return this;
  }

  @Override
  public MeterCollection set(String fieldName, Object value) {
    return (MeterCollection) super.set(fieldName, value);
  }

  @Override
  public MeterCollection clone() {
    return (MeterCollection) super.clone();
  }

}

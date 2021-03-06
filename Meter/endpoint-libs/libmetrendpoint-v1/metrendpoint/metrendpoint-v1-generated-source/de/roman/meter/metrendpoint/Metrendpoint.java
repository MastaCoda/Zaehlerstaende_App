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
 * on 2013-07-26 at 22:07:04 UTC 
 * Modify at your own risk.
 */

package de.roman.meter.metrendpoint;

/**
 * Service definition for Metrendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link MetrendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Metrendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.15.0-rc of the  library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://metertracker.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "metrendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Metrendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Metrendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getMeterCountListWithUserId".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link GetMeterCountListWithUserId#execute()} method to invoke the
   * remote operation.
   *
   * @param meterId
   * @param userId
   * @return the request
   */
  public GetMeterCountListWithUserId getMeterCountListWithUserId(java.lang.Long meterId, java.lang.Long userId) throws java.io.IOException {
    GetMeterCountListWithUserId result = new GetMeterCountListWithUserId(meterId, userId);
    initialize(result);
    return result;
  }

  public class GetMeterCountListWithUserId extends MetrendpointRequest<de.roman.meter.metrendpoint.model.MeterCountCollection> {

    private static final String REST_PATH = "getMeterCountListWithUserId";

    /**
     * Create a request for the method "getMeterCountListWithUserId".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link GetMeterCountListWithUserId#execute()} method to invoke
     * the remote operation. <p> {@link GetMeterCountListWithUserId#initialize(com.google.api.client.g
     * oogleapis.services.AbstractGoogleClientRequest)} must be called to initialize this instance
     * immediately after invoking the constructor. </p>
     *
     * @param meterId
     * @param userId
     * @since 1.13
     */
    protected GetMeterCountListWithUserId(java.lang.Long meterId, java.lang.Long userId) {
      super(Metrendpoint.this, "GET", REST_PATH, null, de.roman.meter.metrendpoint.model.MeterCountCollection.class);
      this.meterId = com.google.api.client.util.Preconditions.checkNotNull(meterId, "Required parameter meterId must be specified.");
      this.userId = com.google.api.client.util.Preconditions.checkNotNull(userId, "Required parameter userId must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetMeterCountListWithUserId setAlt(java.lang.String alt) {
      return (GetMeterCountListWithUserId) super.setAlt(alt);
    }

    @Override
    public GetMeterCountListWithUserId setFields(java.lang.String fields) {
      return (GetMeterCountListWithUserId) super.setFields(fields);
    }

    @Override
    public GetMeterCountListWithUserId setKey(java.lang.String key) {
      return (GetMeterCountListWithUserId) super.setKey(key);
    }

    @Override
    public GetMeterCountListWithUserId setOauthToken(java.lang.String oauthToken) {
      return (GetMeterCountListWithUserId) super.setOauthToken(oauthToken);
    }

    @Override
    public GetMeterCountListWithUserId setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetMeterCountListWithUserId) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetMeterCountListWithUserId setQuotaUser(java.lang.String quotaUser) {
      return (GetMeterCountListWithUserId) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetMeterCountListWithUserId setUserIp(java.lang.String userIp) {
      return (GetMeterCountListWithUserId) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key("MeterId")
    private java.lang.Long meterId;

    /**

     */
    public java.lang.Long getMeterId() {
      return meterId;
    }

    public GetMeterCountListWithUserId setMeterId(java.lang.Long meterId) {
      this.meterId = meterId;
      return this;
    }

    @com.google.api.client.util.Key("UserId")
    private java.lang.Long userId;

    /**

     */
    public java.lang.Long getUserId() {
      return userId;
    }

    public GetMeterCountListWithUserId setUserId(java.lang.Long userId) {
      this.userId = userId;
      return this;
    }

    @Override
    public GetMeterCountListWithUserId set(String parameterName, Object value) {
      return (GetMeterCountListWithUserId) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getMetr".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link GetMetr#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetMetr getMetr(java.lang.Long id) throws java.io.IOException {
    GetMetr result = new GetMetr(id);
    initialize(result);
    return result;
  }

  public class GetMetr extends MetrendpointRequest<de.roman.meter.metrendpoint.model.Meter> {

    private static final String REST_PATH = "meter/{id}";

    /**
     * Create a request for the method "getMetr".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link GetMetr#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetMetr#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetMetr(java.lang.Long id) {
      super(Metrendpoint.this, "GET", REST_PATH, null, de.roman.meter.metrendpoint.model.Meter.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetMetr setAlt(java.lang.String alt) {
      return (GetMetr) super.setAlt(alt);
    }

    @Override
    public GetMetr setFields(java.lang.String fields) {
      return (GetMetr) super.setFields(fields);
    }

    @Override
    public GetMetr setKey(java.lang.String key) {
      return (GetMetr) super.setKey(key);
    }

    @Override
    public GetMetr setOauthToken(java.lang.String oauthToken) {
      return (GetMetr) super.setOauthToken(oauthToken);
    }

    @Override
    public GetMetr setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetMetr) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetMetr setQuotaUser(java.lang.String quotaUser) {
      return (GetMetr) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetMetr setUserIp(java.lang.String userIp) {
      return (GetMetr) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetMetr setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetMetr set(String parameterName, Object value) {
      return (GetMetr) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertMeterCountToUser".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link InsertMeterCountToUser#execute()} method to invoke the
   * remote operation.
   *
   * @param meterId
   * @param userId
   * @param metercountvalue
   * @return the request
   */
  public InsertMeterCountToUser insertMeterCountToUser(java.lang.Long meterId, java.lang.Long userId, java.lang.String metercountvalue) throws java.io.IOException {
    InsertMeterCountToUser result = new InsertMeterCountToUser(meterId, userId, metercountvalue);
    initialize(result);
    return result;
  }

  public class InsertMeterCountToUser extends MetrendpointRequest<de.roman.meter.metrendpoint.model.MeterCount> {

    private static final String REST_PATH = "insertMeterCountToUser";

    /**
     * Create a request for the method "insertMeterCountToUser".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link InsertMeterCountToUser#execute()} method to invoke the
     * remote operation. <p> {@link InsertMeterCountToUser#initialize(com.google.api.client.googleapis
     * .services.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param meterId
     * @param userId
     * @param metercountvalue
     * @since 1.13
     */
    protected InsertMeterCountToUser(java.lang.Long meterId, java.lang.Long userId, java.lang.String metercountvalue) {
      super(Metrendpoint.this, "POST", REST_PATH, null, de.roman.meter.metrendpoint.model.MeterCount.class);
      this.meterId = com.google.api.client.util.Preconditions.checkNotNull(meterId, "Required parameter meterId must be specified.");
      this.userId = com.google.api.client.util.Preconditions.checkNotNull(userId, "Required parameter userId must be specified.");
      this.metercountvalue = com.google.api.client.util.Preconditions.checkNotNull(metercountvalue, "Required parameter metercountvalue must be specified.");
    }

    @Override
    public InsertMeterCountToUser setAlt(java.lang.String alt) {
      return (InsertMeterCountToUser) super.setAlt(alt);
    }

    @Override
    public InsertMeterCountToUser setFields(java.lang.String fields) {
      return (InsertMeterCountToUser) super.setFields(fields);
    }

    @Override
    public InsertMeterCountToUser setKey(java.lang.String key) {
      return (InsertMeterCountToUser) super.setKey(key);
    }

    @Override
    public InsertMeterCountToUser setOauthToken(java.lang.String oauthToken) {
      return (InsertMeterCountToUser) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertMeterCountToUser setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertMeterCountToUser) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertMeterCountToUser setQuotaUser(java.lang.String quotaUser) {
      return (InsertMeterCountToUser) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertMeterCountToUser setUserIp(java.lang.String userIp) {
      return (InsertMeterCountToUser) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key("MeterId")
    private java.lang.Long meterId;

    /**

     */
    public java.lang.Long getMeterId() {
      return meterId;
    }

    public InsertMeterCountToUser setMeterId(java.lang.Long meterId) {
      this.meterId = meterId;
      return this;
    }

    @com.google.api.client.util.Key("UserId")
    private java.lang.Long userId;

    /**

     */
    public java.lang.Long getUserId() {
      return userId;
    }

    public InsertMeterCountToUser setUserId(java.lang.Long userId) {
      this.userId = userId;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String metercountvalue;

    /**

     */
    public java.lang.String getMetercountvalue() {
      return metercountvalue;
    }

    public InsertMeterCountToUser setMetercountvalue(java.lang.String metercountvalue) {
      this.metercountvalue = metercountvalue;
      return this;
    }

    @Override
    public InsertMeterCountToUser set(String parameterName, Object value) {
      return (InsertMeterCountToUser) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertMetr".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link InsertMetr#execute()} method to invoke the remote operation.
   *
   * @param content the {@link de.roman.meter.metrendpoint.model.Meter}
   * @return the request
   */
  public InsertMetr insertMetr(de.roman.meter.metrendpoint.model.Meter content) throws java.io.IOException {
    InsertMetr result = new InsertMetr(content);
    initialize(result);
    return result;
  }

  public class InsertMetr extends MetrendpointRequest<de.roman.meter.metrendpoint.model.Meter> {

    private static final String REST_PATH = "meter";

    /**
     * Create a request for the method "insertMetr".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link InsertMetr#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertMetr#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link de.roman.meter.metrendpoint.model.Meter}
     * @since 1.13
     */
    protected InsertMetr(de.roman.meter.metrendpoint.model.Meter content) {
      super(Metrendpoint.this, "POST", REST_PATH, content, de.roman.meter.metrendpoint.model.Meter.class);
    }

    @Override
    public InsertMetr setAlt(java.lang.String alt) {
      return (InsertMetr) super.setAlt(alt);
    }

    @Override
    public InsertMetr setFields(java.lang.String fields) {
      return (InsertMetr) super.setFields(fields);
    }

    @Override
    public InsertMetr setKey(java.lang.String key) {
      return (InsertMetr) super.setKey(key);
    }

    @Override
    public InsertMetr setOauthToken(java.lang.String oauthToken) {
      return (InsertMetr) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertMetr setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertMetr) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertMetr setQuotaUser(java.lang.String quotaUser) {
      return (InsertMetr) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertMetr setUserIp(java.lang.String userIp) {
      return (InsertMetr) super.setUserIp(userIp);
    }

    @Override
    public InsertMetr set(String parameterName, Object value) {
      return (InsertMetr) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listMetr".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link ListMetr#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListMetr listMetr() throws java.io.IOException {
    ListMetr result = new ListMetr();
    initialize(result);
    return result;
  }

  public class ListMetr extends MetrendpointRequest<de.roman.meter.metrendpoint.model.CollectionResponseMeter> {

    private static final String REST_PATH = "meter";

    /**
     * Create a request for the method "listMetr".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link ListMetr#execute()} method to invoke the remote operation.
     * <p> {@link
     * ListMetr#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListMetr() {
      super(Metrendpoint.this, "GET", REST_PATH, null, de.roman.meter.metrendpoint.model.CollectionResponseMeter.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListMetr setAlt(java.lang.String alt) {
      return (ListMetr) super.setAlt(alt);
    }

    @Override
    public ListMetr setFields(java.lang.String fields) {
      return (ListMetr) super.setFields(fields);
    }

    @Override
    public ListMetr setKey(java.lang.String key) {
      return (ListMetr) super.setKey(key);
    }

    @Override
    public ListMetr setOauthToken(java.lang.String oauthToken) {
      return (ListMetr) super.setOauthToken(oauthToken);
    }

    @Override
    public ListMetr setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListMetr) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListMetr setQuotaUser(java.lang.String quotaUser) {
      return (ListMetr) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListMetr setUserIp(java.lang.String userIp) {
      return (ListMetr) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListMetr setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListMetr setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListMetr set(String parameterName, Object value) {
      return (ListMetr) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeMetr".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link RemoveMetr#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveMetr removeMetr(java.lang.Long id) throws java.io.IOException {
    RemoveMetr result = new RemoveMetr(id);
    initialize(result);
    return result;
  }

  public class RemoveMetr extends MetrendpointRequest<de.roman.meter.metrendpoint.model.Meter> {

    private static final String REST_PATH = "metr/{id}";

    /**
     * Create a request for the method "removeMetr".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link RemoveMetr#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemoveMetr#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveMetr(java.lang.Long id) {
      super(Metrendpoint.this, "DELETE", REST_PATH, null, de.roman.meter.metrendpoint.model.Meter.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveMetr setAlt(java.lang.String alt) {
      return (RemoveMetr) super.setAlt(alt);
    }

    @Override
    public RemoveMetr setFields(java.lang.String fields) {
      return (RemoveMetr) super.setFields(fields);
    }

    @Override
    public RemoveMetr setKey(java.lang.String key) {
      return (RemoveMetr) super.setKey(key);
    }

    @Override
    public RemoveMetr setOauthToken(java.lang.String oauthToken) {
      return (RemoveMetr) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveMetr setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveMetr) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveMetr setQuotaUser(java.lang.String quotaUser) {
      return (RemoveMetr) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveMetr setUserIp(java.lang.String userIp) {
      return (RemoveMetr) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveMetr setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveMetr set(String parameterName, Object value) {
      return (RemoveMetr) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateMetr".
   *
   * This request holds the parameters needed by the the metrendpoint server.  After setting any
   * optional parameters, call the {@link UpdateMetr#execute()} method to invoke the remote operation.
   *
   * @param userId
   * @param meterId
   * @param value
   * @param date
   * @return the request
   */
  public UpdateMetr updateMetr(java.lang.Long userId, java.lang.Long meterId, java.lang.String value, com.google.api.client.util.DateTime date) throws java.io.IOException {
    UpdateMetr result = new UpdateMetr(userId, meterId, value, date);
    initialize(result);
    return result;
  }

  public class UpdateMetr extends MetrendpointRequest<de.roman.meter.metrendpoint.model.Meter> {

    private static final String REST_PATH = "meter/{UserId}/{MeterId}/{Value}/{Date}";

    /**
     * Create a request for the method "updateMetr".
     *
     * This request holds the parameters needed by the the metrendpoint server.  After setting any
     * optional parameters, call the {@link UpdateMetr#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdateMetr#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param userId
     * @param meterId
     * @param value
     * @param date
     * @since 1.13
     */
    protected UpdateMetr(java.lang.Long userId, java.lang.Long meterId, java.lang.String value, com.google.api.client.util.DateTime date) {
      super(Metrendpoint.this, "PUT", REST_PATH, null, de.roman.meter.metrendpoint.model.Meter.class);
      this.userId = com.google.api.client.util.Preconditions.checkNotNull(userId, "Required parameter userId must be specified.");
      this.meterId = com.google.api.client.util.Preconditions.checkNotNull(meterId, "Required parameter meterId must be specified.");
      this.value = com.google.api.client.util.Preconditions.checkNotNull(value, "Required parameter value must be specified.");
      this.date = com.google.api.client.util.Preconditions.checkNotNull(date, "Required parameter date must be specified.");
    }

    @Override
    public UpdateMetr setAlt(java.lang.String alt) {
      return (UpdateMetr) super.setAlt(alt);
    }

    @Override
    public UpdateMetr setFields(java.lang.String fields) {
      return (UpdateMetr) super.setFields(fields);
    }

    @Override
    public UpdateMetr setKey(java.lang.String key) {
      return (UpdateMetr) super.setKey(key);
    }

    @Override
    public UpdateMetr setOauthToken(java.lang.String oauthToken) {
      return (UpdateMetr) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateMetr setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateMetr) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateMetr setQuotaUser(java.lang.String quotaUser) {
      return (UpdateMetr) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateMetr setUserIp(java.lang.String userIp) {
      return (UpdateMetr) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key("UserId")
    private java.lang.Long userId;

    /**

     */
    public java.lang.Long getUserId() {
      return userId;
    }

    public UpdateMetr setUserId(java.lang.Long userId) {
      this.userId = userId;
      return this;
    }

    @com.google.api.client.util.Key("MeterId")
    private java.lang.Long meterId;

    /**

     */
    public java.lang.Long getMeterId() {
      return meterId;
    }

    public UpdateMetr setMeterId(java.lang.Long meterId) {
      this.meterId = meterId;
      return this;
    }

    @com.google.api.client.util.Key("Value")
    private java.lang.String value;

    /**

     */
    public java.lang.String getValue() {
      return value;
    }

    public UpdateMetr setValue(java.lang.String value) {
      this.value = value;
      return this;
    }

    @com.google.api.client.util.Key("Date")
    private com.google.api.client.util.DateTime date;

    /**

     */
    public com.google.api.client.util.DateTime getDate() {
      return date;
    }

    public UpdateMetr setDate(com.google.api.client.util.DateTime date) {
      this.date = date;
      return this;
    }

    @Override
    public UpdateMetr set(String parameterName, Object value) {
      return (UpdateMetr) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Metrendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Metrendpoint}. */
    @Override
    public Metrendpoint build() {
      return new Metrendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link MetrendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setMetrendpointRequestInitializer(
        MetrendpointRequestInitializer metrendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(metrendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}

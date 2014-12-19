
package service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Generated(value = {
    "wadl|http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/application.wadl"
}, comments = "wadl2java, http://wadl.java.net", date = "2014-12-19T09:25:52.442+01:00")
public class SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest {

    /**
     * The base URI for the resource represented by this proxy
     * 
     */
    public final static URI BASE_URI;

    static {
        URI originalURI = URI.create("http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/");
        // Look up to see if we have any indirection in the local copy
        // of META-INF/java-rs-catalog.xml file, assuming it will be in the
        // oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
        java.io.InputStream is = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.class.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
        if (is!=null) {
            try {
                // Ignore the namespace in the catalog, can't use wildcard until
                // we are sure we have XPath 2.0
                String found = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(
                    "/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI +"']/@uri", 
                    new org.xml.sax.InputSource(is)); 
                if (found!=null && found.length()>0) {
                    originalURI = java.net.URI.create(found);
                }
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    is.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        BASE_URI = originalURI;
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events events(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events(client, baseURI);
    }

    /**
     * Template method to allow tooling to customize the new Client
     * 
     */
    private static void customizeClientConfiguration(ClientConfig cc) {
    }

    /**
     * Template method to allow tooling to override Client factory
     * 
     */
    private static Client createClientInstance(ClientConfig cc) {
        return Client.create(cc);
    }

    /**
     * Create a new Client instance
     * 
     */
    public static Client createClient() {
        ClientConfig cc = new DefaultClientConfig();
        customizeClientConfiguration(cc);
        return createClientInstance(cc);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events events() {
        return events(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events events(Client client) {
        return events(client, BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video video(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video(client, baseURI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video video() {
        return video(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video video(Client client) {
        return video(client, BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps apps(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps(client, baseURI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps apps() {
        return apps(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps apps(Client client) {
        return apps(client, BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules rules(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules(client, baseURI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules rules() {
        return rules(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules rules(Client client) {
        return rules(client, BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users users(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users(client, baseURI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users users() {
        return users(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users users(Client client) {
        return users(client, BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices devices(Client client, URI baseURI) {
        return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices(client, baseURI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices devices() {
        return devices(createClient(), BASE_URI);
    }

    public static SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices devices(Client client) {
        return devices(client, BASE_URI);
    }

    public static class Apps {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Apps(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/apps");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Apps(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.AddApp addApp() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.AddApp(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAppByID getAppByID() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAppByID(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAllApps getAllApps() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAllApps(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetApp getApp() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetApp(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetApps getApps() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetApps(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDevices getDevices() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDevices(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAppList getAppList() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAppList(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDeviceCount getDeviceCount() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDeviceCount(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDevicesWithCamera getDevicesWithCamera() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetDevicesWithCamera(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.DeleteApp deleteApp() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.DeleteApp(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.UpdateApp updateApp() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.UpdateApp(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddApp {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddApp(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addApp");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddApp(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public App getAsApp(String mac, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(String mac, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class DeleteApp {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public DeleteApp(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/deleteApp");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private DeleteApp(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse delete() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse delete(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAllApps {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAllApps(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAllApps");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAllApps(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetApp {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetApp(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getApp");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetApp(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public App getAsApp(String mac, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(String mac, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAppByID {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAppByID(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAppByID");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAppByID(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public App getAsApp(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAppList {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAppList(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAppList");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAppList(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public AppList getAsAppList() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(AppList.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetApps {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetApps(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getApps");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetApps(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public App getAsApp(String mac) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(String mac, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetDeviceCount {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetDeviceCount(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getDeviceCount");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetDeviceCount(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetDevices {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetDevices(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getDevices");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetDevices(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetDevicesWithCamera {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetDevicesWithCamera(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getDevicesWithCamera");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetDevicesWithCamera(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public App getAsApp() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(App.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class UpdateApp {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public UpdateApp(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/updateApp");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private UpdateApp(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse put() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse put(String mac, String eventtype, Boolean status) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                localUriBuilder = localUriBuilder.replaceQueryParam("status", status);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(String mac, String eventtype, Boolean status, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                localUriBuilder = localUriBuilder.replaceQueryParam("status", status);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(String mac, String eventtype, Boolean status, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                localUriBuilder = localUriBuilder.replaceQueryParam("status", status);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Devices {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Devices(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/devices");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Devices(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.UpdateDeviceName updateDeviceName() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.UpdateDeviceName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.GetDeviceList getDeviceList() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.GetDeviceList(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.AddDeviceName addDeviceName() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.AddDeviceName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.GetDevice getDevice() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Devices.GetDevice(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddDeviceName {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddDeviceName(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addDeviceName");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddDeviceName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Device getAsDevice() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Device.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Device getAsDevice(String mac, String name) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Device.class);
            }

            public<T >T getAsJson(String mac, String name, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, String name, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetDevice {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetDevice(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getDevice");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetDevice(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Device getAsDevice() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Device.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Device getAsDevice(String mac) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Device.class);
            }

            public<T >T getAsJson(String mac, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetDeviceList {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetDeviceList(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getDeviceList");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetDeviceList(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Device getAsDevice() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Device.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class UpdateDeviceName {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public UpdateDeviceName(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/updateDeviceName");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private UpdateDeviceName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse put() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse put(String mac, String name) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(String mac, String name, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(String mac, String name, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Events {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Events(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/events");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Events(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AddEventByMac addEventByMac() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AddEventByMac(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AwaitEventByMac awaitEventByMac() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AwaitEventByMac(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByMac getEventsByMac() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByMac(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByMacAndType getEventsByMacAndType() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByMacAndType(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AddEventByID addEventByID() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AddEventByID(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByID getEventsByID() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByID(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetAllEvents getAllEvents() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetAllEvents(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByIdInTimeSpan getEventsByIdInTimeSpan() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByIdInTimeSpan(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AwaitEventByID awaitEventByID() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AwaitEventByID(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByType getEventsByType() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByType(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetTotalEventCountInTimeSpan getTotalEventCountInTimeSpan() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetTotalEventCountInTimeSpan(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddEventByID {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddEventByID(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addEventByID");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddEventByID(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(Integer value, Integer id, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(Integer value, Integer id, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer value, Integer id, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class AddEventByMac {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddEventByMac(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addEventByMac");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddEventByMac(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(String mac, Integer value, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(String mac, Integer value, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, Integer value, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("value", value);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class AwaitEventByID {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AwaitEventByID(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/awaitEventByID");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AwaitEventByID(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(Integer id, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(Integer id, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class AwaitEventByMac {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AwaitEventByMac(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/awaitEventByMac");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AwaitEventByMac(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(String mac, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(String mac, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAllEvents {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAllEvents(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAllEvents");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAllEvents(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetEventsByID {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetEventsByID(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getEventsByID");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetEventsByID(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetEventsByIdInTimeSpan {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetEventsByIdInTimeSpan(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getEventsByIdInTimeSpan");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetEventsByIdInTimeSpan(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(Integer id, Integer time) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("time", time);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(Integer id, Integer time, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("time", time);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Integer time, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("time", time);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetEventsByMac {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetEventsByMac(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getEventsByMac");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetEventsByMac(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(String mac) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(String mac, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetEventsByMacAndType {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetEventsByMacAndType(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getEventsByMacAndType");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetEventsByMacAndType(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(String mac, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(String mac, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String mac, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("mac", mac);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetEventsByType {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetEventsByType(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getEventsByType");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetEventsByType(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Event getAsEvent() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Event getAsEvent(Integer id, String eventtype) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Event.class);
            }

            public<T >T getAsJson(Integer id, String eventtype, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, String eventtype, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("eventType", eventtype);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetTotalEventCountInTimeSpan {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetTotalEventCountInTimeSpan(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getTotalEventCountInTimeSpan");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetTotalEventCountInTimeSpan(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(Integer time, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("time", time);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer time, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("time", time);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Rules {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Rules(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/rules");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Rules(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.DeletePolicy deletePolicy() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.DeletePolicy(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetPolicyByName getPolicyByName() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetPolicyByName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetAllRuleStrings getAllRuleStrings() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetAllRuleStrings(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.SetSecurityLevel setSecurityLevel() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.SetSecurityLevel(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetPolicy getPolicy() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetPolicy(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetAllPolicies getAllPolicies() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetAllPolicies(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetSecurityLevel getSecurityLevel() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetSecurityLevel(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.AddPolicy addPolicy() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.AddPolicy(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.AddRuleString addRuleString() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.AddRuleString(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetRuleString getRuleString() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Rules.GetRuleString(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddPolicy {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddPolicy(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addPolicy");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddPolicy(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Policy getAsPolicy() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Policy getAsPolicy(String name) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(String name, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String name, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class AddRuleString {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddRuleString(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addRuleString");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddRuleString(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T postTextPlainAsJson(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postTextPlainAsJson(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T postTextPlainAsJson(Object input, Integer policyid, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("policyId", policyid);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postTextPlainAsJson(Object input, Integer policyid, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("policyId", policyid);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class DeletePolicy {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public DeletePolicy(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/deletePolicy");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private DeletePolicy(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse delete() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse delete(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAllPolicies {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAllPolicies(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAllPolicies");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAllPolicies(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Policy getAsPolicy() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetAllRuleStrings {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetAllRuleStrings(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getAllRuleStrings");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetAllRuleStrings(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public RuleString getAsRuleString() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RuleString.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetPolicy {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetPolicy(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getPolicy");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetPolicy(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Policy getAsPolicy() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Policy getAsPolicy(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetPolicyByName {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetPolicyByName(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getPolicyByName");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetPolicyByName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public Policy getAsPolicy() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public Policy getAsPolicy(String name) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(Policy.class);
            }

            public<T >T getAsJson(String name, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String name, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("name", name);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetRuleString {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetRuleString(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getRuleString");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetRuleString(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public RuleString getAsRuleString() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RuleString.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public RuleString getAsRuleString(Integer id) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(RuleString.class);
            }

            public<T >T getAsJson(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetSecurityLevel {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetSecurityLevel(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getSecurityLevel");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetSecurityLevel(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class SetSecurityLevel {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public SetSecurityLevel(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/setSecurityLevel");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private SetSecurityLevel(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse put() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse put(Integer level) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("level", level);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                return response;
            }

            public<T >T put(Integer level, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("level", level);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T put(Integer level, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("level", level);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Users {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Users(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/users");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Users(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.DeleteUser deleteUser() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.DeleteUser(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetUserByUserName getUserByUserName() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetUserByUserName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetLastLoginByUserName getLastLoginByUserName() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetLastLoginByUserName(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetUsers getUsers() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.GetUsers(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.AddUser addUser() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Users.AddUser(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddUser {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddUser(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addUser");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddUser(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public User getAsUser() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(User.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public User getAsUser(String username, String email, String firstname, String lastname, String role, String password) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                localUriBuilder = localUriBuilder.replaceQueryParam("email", email);
                localUriBuilder = localUriBuilder.replaceQueryParam("firstName", firstname);
                localUriBuilder = localUriBuilder.replaceQueryParam("lastName", lastname);
                localUriBuilder = localUriBuilder.replaceQueryParam("role", role);
                localUriBuilder = localUriBuilder.replaceQueryParam("password", password);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(User.class);
            }

            public<T >T getAsJson(String username, String email, String firstname, String lastname, String role, String password, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                localUriBuilder = localUriBuilder.replaceQueryParam("email", email);
                localUriBuilder = localUriBuilder.replaceQueryParam("firstName", firstname);
                localUriBuilder = localUriBuilder.replaceQueryParam("lastName", lastname);
                localUriBuilder = localUriBuilder.replaceQueryParam("role", role);
                localUriBuilder = localUriBuilder.replaceQueryParam("password", password);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String username, String email, String firstname, String lastname, String role, String password, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                localUriBuilder = localUriBuilder.replaceQueryParam("email", email);
                localUriBuilder = localUriBuilder.replaceQueryParam("firstName", firstname);
                localUriBuilder = localUriBuilder.replaceQueryParam("lastName", lastname);
                localUriBuilder = localUriBuilder.replaceQueryParam("role", role);
                localUriBuilder = localUriBuilder.replaceQueryParam("password", password);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class DeleteUser {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public DeleteUser(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/deleteUser");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private DeleteUser(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public ClientResponse delete() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public ClientResponse delete(String username) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response;
            }

            public<T >T delete(String username, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T delete(String username, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetLastLoginByUserName {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetLastLoginByUserName(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getLastLoginByUserName");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetLastLoginByUserName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(String username, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String username, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetUserByUserName {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetUserByUserName(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getUserByUserName");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetUserByUserName(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public User getAsUser() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(User.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public User getAsUser(String username) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(User.class);
            }

            public<T >T getAsJson(String username, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(String username, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("userName", username);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetUsers {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetUsers(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getUsers");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetUsers(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public User getAsUser() {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(User.class);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Video {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Video(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/video");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Video(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.AddVideo addVideo() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.AddVideo(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.DeleteEVERYTHING deleteEVERYTHING() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.DeleteEVERYTHING(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetVideo getVideo() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetVideo(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetLatest getLatest() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetLatest(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetVODS getVODS() {
            return new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Video.GetVODS(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class AddVideo {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public AddVideo(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/addVideo");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private AddVideo(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T postVideoMp4AsTextPlain(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postVideoMp4AsTextPlain(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T postVideoMp4AsTextPlain(Object input, Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T postVideoMp4AsTextPlain(Object input, Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class DeleteEVERYTHING {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public DeleteEVERYTHING(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/deleteEVERYTHING");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private DeleteEVERYTHING(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsTextPlain(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextPlain(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsTextPlain(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextPlain(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetLatest {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetLatest(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getLatest");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetLatest(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsTextPlain(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextPlain(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsTextPlain(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsTextPlain(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetVODS {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetVODS(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getVODS");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetVODS(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(Integer id, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Integer id, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

        public static class GetVideo {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public GetVideo(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/getVideo");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private GetVideo(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsVideoMp4(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsVideoMp4(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsVideoMp4(Integer id, Integer count, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("count", count);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsVideoMp4(Integer id, Integer count, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                localUriBuilder = localUriBuilder.replaceQueryParam("id", id);
                localUriBuilder = localUriBuilder.replaceQueryParam("count", count);
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("video/mp4");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }


    /**
     * Workaround for JAX_RS_SPEC-312
     * 
     */
    private static class WebApplicationExceptionMessage
        extends WebApplicationException
    {


        private WebApplicationExceptionMessage(Response response) {
            super(response);
        }

        /**
         * Workaround for JAX_RS_SPEC-312
         * 
         */
        public String getMessage() {
            Response response = getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            if (status!= null) {
                return (response.getStatus()+(" "+ status.getReasonPhrase()));
            } else {
                return Integer.toString(response.getStatus());
            }
        }

        public String toString() {
            String s = "javax.ws.rs.WebApplicationException";
            String message = getLocalizedMessage();
            return (s +(": "+ message));
        }

    }

}

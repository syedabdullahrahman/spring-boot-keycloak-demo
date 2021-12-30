Keycloak is an identity and access management solution that we can use in our architecture to provide authentication and authorization services,

- Realm
    - It's a domain in which we apply specific security policies

- Client adapters
    - Keycloak client adapters are libraries that make it very easy to secure applications and services with Keycloak.
    
- `@KeycloakConfiguration`
    - `@KeycloakConfiguration = @Configuration + @EnableWebSecurity`
    
    
- Keycloak as the Authentication Provider
    - Keycloak is our identity provider (IdP), need to register it with the Spring Security authentication manager.
  
Keycloak is an identity and access management solution that we can use in our architecture to provide authentication and authorization services,

- Realm
    - It's a domain in which we apply specific security policies. A Realm manages a set of users, credentials, roles, and groups. A user belongs to and logs into a realm. Realms are isolated from one another and can only manage and authenticate the users that they control.

- Client
    - Clients are entities that can request Keycloak to authenticate a user. Most often, clients are applications and services that want to use Keycloak to secure themselves and provide a single sign-on solution. Clients can also be entities that just want to request identity information or an access token so that they can securely invoke other services on the network that are secured by Keycloak.
- Client adapters
    - Keycloak client adapters are libraries that make it very easy to secure applications and services with Keycloak.
    
- `@KeycloakConfiguration`
    - `@KeycloakConfiguration = @Configuration + @EnableWebSecurity`
    
    
- Keycloak as the Authentication Provider
    - Keycloak is our identity provider (IdP), need to register it with the Spring Security authentication manager.
  
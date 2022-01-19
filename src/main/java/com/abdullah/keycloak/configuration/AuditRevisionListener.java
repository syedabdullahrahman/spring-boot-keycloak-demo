package com.abdullah.keycloak.configuration;

import com.abdullah.keycloak.model.AuditRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        String currentUser =(((KeycloakPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getKeycloakSecurityContext().getToken().getPreferredUsername());
        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUser(currentUser);
        audit.setUser(currentUser);
    }
}
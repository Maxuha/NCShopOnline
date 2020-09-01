FROM container-registry.oracle.com/middleware/weblogic:12.2.1.3
ENV DOMAIN_HOME /u01/oracle/user_projects/domains/base_domain
ENV DEPLOY_DIR $DOMAIN_HOME/autodeploy
RUN mkdir -p $DEPLOY_DIR && \
    chmod a+xr $DEPLOY_DIR && \
    chown oracle:oracle $DEPLOY_DIR && \
    mkdir -p $DOMAIN_HOME/lib && \
    chmod a+xr $DOMAIN_HOME/lib && \
    chown oracle:oracle $DOMAIN_HOME/lib && \
    mkdir -p $DOMAIN_HOME/config/jdbc
COPY src/main/webapp/WEB-INF/lib/postgresql-42.2.5.jar $DOMAIN_HOME/lib/postgresql-42.2.5.jar
COPY domain.properties /u01/oracle/properties/
COPY target/ncshoponline.war $DEPLOY_DIR/ncshoponline.war
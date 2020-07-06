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
COPY target/ncshoponline-1.0-SNAPSHOT.war $DEPLOY_DIR/ncshoponline-1.0-SNAPSHOT.war
COPY src/main/webapp/WEB-INF/lib/NCShopOnlineDB-1060-jdbc.xml $DOMAIN_HOME/config/jdbc/NCShopOnlineDB-1060-jdbc.xml
RUN chmod 777 $DOMAIN_HOME/config && \
    chown oracle:oracle $DOMAIN_HOME/config
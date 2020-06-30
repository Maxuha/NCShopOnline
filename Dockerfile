FROM busybox
ENV DOMAIN_HOME /u01/oracle/user_projects/domains/base_domain
ENV DEPLOY_DIR $DOMAIN_HOME/autodeploy
COPY target/ncshoponline-1.0-SNAPSHOT.war $DEPLOY_DIR/sample.war
VOLUME $DEPLOY_DIR
FROM amazonlinux:2

#Configuration specific to amazon docker base image starts here
ARG version=17.0.11.9-1
RUN set -eux \
    && export GNUPGHOME="$(mktemp -d)" \
    && curl -fL -o corretto.key https://yum.corretto.aws/corretto.key \
    && gpg --batch --import corretto.key \
    && gpg --batch --export --armor '6DC3636DAE534049C8B94623A122542AB04F24E3' > corretto.key \
    && rpm --import corretto.key \
    && rm -r "$GNUPGHOME" corretto.key \
    && curl -fL -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo \
    && grep -q '^gpgcheck=1' /etc/yum.repos.d/corretto.repo \
    && echo "priority=9" >> /etc/yum.repos.d/corretto.repo \
    && yum install -y java-17-amazon-corretto-devel-$version \
    && (find /usr/lib/jvm/java-17-amazon-corretto -name src.zip -delete || true) \
    && yum install -y fontconfig \
    && yum clean all
ENV LANG C.UTF-8
ENV JAVA_HOME=/usr/lib/jvm/java-17-amazon-corretto
#Configuration specific to amazon docker base image ends here

COPY target/gatewayservice-0.0.1-SNAPSHOT.jar gatewayservice.jar

ENTRYPOINT [ "java", "-jar","-Deureka.client.service-url.defaultZone=http://discoveryservicelb.default.svc.cluster.local:8761/eureka", "-Dspring.config.import=configserver:http://configurationservicelb.default.svc.cluster.local:8762/config","catalogservice.jar"]

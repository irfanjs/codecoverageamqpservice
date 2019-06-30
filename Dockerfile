FROM    centos

ENV     UPDATE_VERSION=8u212
ENV     JAVA_VERSION=1.8.0_212
ENV     BUILD=b04

ENV     JAVA_HOME=rootjdk${UPDATE_VERSION}-${BUILD}

RUN     yum -y update && 
        yum -y install wget && 
        cd root && 
        wget httpsgithub.comAdoptOpenJDKopenjdk8-binariesreleasesdownloadjdk${UPDATE_VERSION}-${BUILD}OpenJDK8U-jdk_x64_linux_hotspot_${UPDATE_VERSION}${BUILD}.tar.gz && 
        gunzip OpenJDK8U-jdk_x64_linux_hotspot_${UPDATE_VERSION}${BUILD}.tar.gz && 
        tar -xvpf OpenJDK8U-jdk_x64_linux_hotspot_${UPDATE_VERSION}${BUILD}.tar && 
        alternatives --install usrbinjava java rootjdk${UPDATE_VERSION}-${BUILD}binjava 1 && 
        alternatives --set java rootjdk${UPDATE_VERSION}-${BUILD}binjava && 
        export JAVA_HOME=rootjdk${UPDATE_VERSION}-${BUILD} && 
        echo export JAVA_HOME=rootjdk${UPDATE_VERSION}-${BUILD}  tee etcenvironment && 
        source etcenvironment

ADD ./target/codecoverageamqpservice-0.0.1-SNAPSHOT.jar /opt

CMD ["java","-jar","/opt/codecoverageamqpservice-0.0.1-SNAPSHOT.jar"]
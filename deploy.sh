#!/bin/bash
# last modified by xujiaqing at 2017-10-27
set -u

# 自动化打包发布
# build 需要的参数: appenv(prod/dev/beta)  verision
# run  需要的参数   

#默认参数
appenv="dev"
version="latest"
appname="web_suite"
port="6002"

help () {
        cat <<EOF
Usage: $0 [OPTIONS]
Options: sh deploy.sh -a dev -v 0.1

Options:
    --appenv            -a  [prod|dev|beta]the appenv default dev
    --version           -v  version tag
EOF
}


while test $# -gt 0
do
    case $1 in
        --appenv|-a)
            appenv=$2
            shift
            ;;
        --version|-v)
            version=$2
            shift
            ;;
        --help)
            help
            exit 0
            ;;
        *)
            echo >&2 "Invalid argument: $1"
            ;;
    esac
    shift
done

# check who i am, the parameter is given correctly
if [ `whoami` != 'root' ]; then
    echo "You are not the user of root"
    exit -1
fi

if [ "${appenv}" != "prod" -a "${appenv}" != "dev" -a "${appenv}" != "beta" ]; then
    help
    echo "parameter error: the appenv must be prod or dev  or beta"
    exit -1
fi

case $appenv in
    prod)
        port=8080
        ;;
    dev)
        port=6002
        ;;
    beta)
        prot=6003
        ;;
esac




tag=${appname}/${appenv}:${version}
name=${appname}_${appenv}_${version}

#echo $tag
#echo $name

#创建镜像
docker build -t $tag .

#关闭该环境的所有容器实例
docker ps|grep ${appname}/${appenv} |awk {'print $NF'}| xargs docker stop

#开启对应镜像的实例
docker run -t -i -d --name $name -p$port:8080 $tag 

#启动实例中的tomcat
docker exec  $name /bin/bash -c "/var/apache-tomcat-8.0.46/bin/startup.sh"

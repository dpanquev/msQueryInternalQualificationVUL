oc delete cm configmap-query-internal-qualification --ignore-not-found=true -n $1
oc create -f despliegue/desarrollo/configmap-query-internal-qualification.yaml -n $1
oc delete secret secret-query-internal-qualification --ignore-not-found=true -n $1
oc create -f despliegue/desarrollo/secret-query-internal-qualification.yaml -n $1
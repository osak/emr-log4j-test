Path to Yarn logs:
- Master: /var/log/hadoop/steps/XXX
- Slave: /var/log/hadoop-yarn/containers/application_XXX/container_XXX/std(out|err)
 
https://forums.aws.amazon.com/thread.jspa?threadID=220552

Configure Spark on EMR:
https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-configure.html

How to change EMR config:
https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-configure-apps.html#w2aac15c26
(See "Supplying a Configuration in the Console")

See log in Console: Cluster > Application History > (app link) > Executors

`--packages` nor fat jar seems not effective. Probably 3rd party lib must be on YARN's classpath

use `${spark.yarn.app.container.log.dir}` to get log directory of YARN
https://medium.com/@iacomini.riccardo/spark-logging-configuration-in-yarn-faf5ba5fdb01

yarn.application.classpath

YARN classpath
https://stackoverflow.com/questions/43404236/how-to-change-yarn-scheduler-configuration-on-aws-emr

import pika
import ssl

# AWS
# server_hostname = "b-ae2c1947-b72b-4fcb-8dd8-23086319352e.mq.sa-east-1.amazonaws.com"
# credentials = pika.PlainCredentials('spring_mq_user', 'spring_mq_pass')
# context = ssl.create_default_context()
# ssl_options = pika.SSLOptions(context=context, server_hostname=server_hostname)
# parameters = pika.ConnectionParameters(host=server_hostname,
#                                        port=5671,
#                                        credentials=credentials,
#                                        ssl_options=ssl_options)

# Local
server_hostname = "localhost"
credentials = pika.PlainCredentials('spring_mq_user', 'spring_mq_pass')
parameters = pika.ConnectionParameters(host=server_hostname,
                                       port=5672,
                                       credentials=credentials)


def create_connection():
    return pika.BlockingConnection(parameters)


import logging
from pika.exchange_type import ExchangeType
from server_mq import create_connection

# logging.basicConfig(level=logging.INFO)

connection = create_connection()

# Payment
queue = "payment.v1.process"
exchange = "teste_ex"
channel = connection.channel()
channel.exchange_declare(exchange=exchange, exchange_type=ExchangeType.fanout, durable=True)
channel.queue_declare(queue=queue, durable=True)
channel.queue_bind(queue=queue, exchange=exchange)
channel.basic_publish(exchange, "", "Olá, setup".encode())
channel.close()

# Email
queue = "email.v1.sendemail"
exchange = "email.direct"
routing_key = "send/email"
channel = connection.channel()
channel.exchange_declare(exchange=exchange, exchange_type=ExchangeType.direct, durable=True)
channel.queue_declare(queue=queue, durable=True)
channel.queue_bind(queue=queue, exchange=exchange, routing_key=routing_key)
channel.basic_publish(exchange, routing_key, "Olá, setup".encode())
channel.close()

connection.close()


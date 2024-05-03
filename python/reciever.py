from server_mq import create_connection

connection = create_connection()

channel = connection.channel()
result = channel.queue_declare(queue='payment.v1.process', exclusive=False, durable=True)
queue_name = result.method.queue

print(' [*] Waiting for logs. To exit press CTRL+C')


def callback(ch, method, properties, body):
    print(f" [x] Received {body.decode()}")
    ch.basic_ack(delivery_tag=method.delivery_tag)


channel.basic_qos(prefetch_count=1)
channel.basic_consume(queue=queue_name, on_message_callback=callback)

channel.start_consuming()

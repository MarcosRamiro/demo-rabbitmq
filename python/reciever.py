from server_mq import create_connection
import fastavro
from fastavro.schema import load_schema
from fastavro import reader
from io import BytesIO

schema = load_schema("address_book.avsc")

connection = create_connection()

channel = connection.channel()
result = channel.queue_declare(queue='payment.v1.process', exclusive=False, durable=True)
queue_name = result.method.queue

print(' [*] Waiting for logs. To exit press CTRL+C')


def callback(ch, method, properties, body):
    # print(f" [x] Received {body}")
    with BytesIO(body) as f:
        avro_reader = fastavro.schemaless_reader(f, schema)
    print(type(avro_reader))
    print(f" [x] Received {avro_reader}")
    ch.basic_ack(delivery_tag=method.delivery_tag)


channel.basic_qos(prefetch_count=1)
channel.basic_consume(queue=queue_name, on_message_callback=callback)

channel.start_consuming()

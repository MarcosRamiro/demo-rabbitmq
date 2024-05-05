import fastavro
from fastavro.schema import load_schema
from io import BytesIO
from server_mq import create_connection


connection = create_connection()
exchange = "teste_ex"
channel = connection.channel()

numero = 0

# Seu objeto de exemplo
objeto = {
    'name': 'Marcos Ramiro',
    'id': 1234,
    'email': 'marcos@email.com',
    'phones': [
        {
            'number': '011993795588',
            'phone_type': 'PHONE_TYPE_HOME'
        }
    ]
}

# Carregando o esquema Avro do arquivo
schema = load_schema("address_book.avsc")

# Convertendo o objeto em bytes Avro
with BytesIO() as out:
    fastavro.schemaless_writer(out, schema, objeto)
    out.seek(0)
    avro_bytes = out.read()

print(avro_bytes)
channel.basic_publish(exchange="teste_ex", routing_key="", body=avro_bytes)

channel.close()
connection.close()


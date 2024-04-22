from server_mq import create_connection

connection = create_connection()
exchange = "teste_ex"
channel = connection.channel()

numero = 0

while numero < 100:
    channel.basic_publish(exchange="teste_ex", routing_key="", body=str(numero).encode())
    numero = numero + 1


channel.close()
connection.close()


import { connect } from "./mq_server.js"; 

const queue = 'payment.v1.process';
const sendEmailExchange = 'email.direct';

;(async () => {
    
    const conn = await connect();

    const ch1 = await conn.createChannel();
    await ch1.assertQueue(queue);
    ch1.prefetch(1);

    const ch2 = await conn.createChannel();
    await ch2.assertExchange(sendEmailExchange, 'direct', { durable: true})

    await ch1.consume(queue, async (msg) => {
        if (msg !== null){
            ch1.ack(msg);
            
            // let mensagem = msg.content.toString();
            // if (mensagem !== null && mensagem === 'sair') {
            //     let consumerTag = msg.fields.consumerTag;
            //     console.log('Consumer cancelled by user!!');
            //     console.log(`ConsumerTag: ${consumerTag}`);

            //     await ch1.cancel(consumerTag);
            //     await ch1.close();
            //     await ch2.close();
            //     await conn.close();
            //     return;
            // }
            
            ch2.publish(sendEmailExchange, 'send/email', msg.content);
            
        } else {
            console.log('Consumer cancelled by server');
        }
    });

    console.log(`Escutando a fila ${queue}...`);

    // let json = '{"nome":"Teste Performance","email":"mara@email.com"}';
    // const bytes = Buffer.byteLength(json, 'utf8');
    // console.log('Tamanho em bytes:', bytes);

})();

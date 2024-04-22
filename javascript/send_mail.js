import { connect } from "./mq_server.js";
import { appendFile } from "node:fs/promises";

const queue = 'email.v1.sendemail';

async function escreverArquivo(msg) {
    try {
      await appendFile('email.csv', msg + '\n');
    } catch (err) {
      console.log(err);
    }
}

;(async () => {
    
    const conn = await connect();

    const ch1 = await conn.createChannel();
    await ch1.assertQueue(queue);
    ch1.prefetch(1);

    await ch1.consume(queue, async (msg) => {
        if (msg !== null){
            escreverArquivo(`email;${msg.content.toString()};`);
            ch1.ack(msg);
        } else {
            console.log('Consumer cancelled by server');
        }
    });

    console.log(`Escutando a fila ${queue}...`)

})();

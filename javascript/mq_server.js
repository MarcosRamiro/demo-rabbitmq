import { connect as conn } from 'amqplib';

const connectionParamsAWS = {
    protocol: 'amqps',
    hostname: 'b-ae2c1947-b72b-4fcb-8dd8-23086319352e.mq.sa-east-1.amazonaws.com',
    port: 5671,
    username: 'spring_mq_user',
    password: 'spring_mq_pass',
    // frameMax: 0x2000, // 8Kb (2^n)
}

const connectionParamsLocal = {
    protocol: 'amqp',
    hostname: 'localhost',
    port: 5672,
    username: 'spring_mq_user',
    password: 'spring_mq_pass',
    // frameMax: 0x2000, // 8Kb (2^n)
}

export async function connect() {
    return await conn(connectionParamsLocal);
}
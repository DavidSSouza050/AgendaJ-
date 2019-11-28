const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const server = require('http').Server(app);
const io = require('./lib/socket').listen(server);
app.set('socketio', io);

app.use(cors());
app.use(bodyParser.urlencoded({extended : true}));
app.use(express.json());

server.listen(3355);
const express = require('express');
const app = express();
const axios = require('axios');

app.use((req, res, next) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    next();
});

app.get('/', async (req, res) => {
    try {
        const response = await axios.get('http://localhost:8080/schemas');
        console.log(response.data);
        res.status(200).send(response.data);
    } catch (error) {
        console.error(error);
        res.status(500).send('Error retrieving schemas.');
    }
});


app.listen(9000, () => {
    console.log('Server started on port 9000');
});

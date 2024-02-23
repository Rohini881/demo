const express = require('express');
const bodyParser = require('body-parser');
const translate = require('google-translate-api');

const app = express();
const port = 3000; // You can choose any available port

app.use(bodyParser.json());

app.post('/translate', async (req, res) => {
    try {
        const { text } = req.body;

        if (!text) {
            return res.status(400).json({ error: 'Text not provided in the request' });
        }

        // Translate the text from English to French
        const translation = await translate(text, { from: 'en', to: 'fr' });

        // Return the translated text in the response
        res.json({ translatedText: translation.text });
    } catch (error) {
        console.error('Translation error:', error);
        res.status(500).json({ error: 'Translation error' });
    }
});

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
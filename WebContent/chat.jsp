<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chatbot AI</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .chat-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .chatbox {
            width: 100%;
            height: 300px;
            overflow-y: auto;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .input-container {
            display: flex;
            justify-content: space-between;
        }
        input[type="text"] {
            width: 80%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            width: 15%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="chat-container">
    <h2>Chatbot AI</h2>
    <div class="chatbox" id="chatbox">
        <div><b>You:</b> <span>${userInput}</span></div>
        <div><b>Bot:</b> <span>${response}</span></div>
    </div>

    <form action="chat" method="post">
        <div class="input-container">
            <input type="text" name="userInput" placeholder="Ask me anything..." required />
            <button type="submit">Send</button>
        </div>
    </form>
</div>

</body>
</html>

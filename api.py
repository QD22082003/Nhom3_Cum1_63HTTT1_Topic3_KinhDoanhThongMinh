import pandas as pd
from flask import Flask, request, jsonify
import google.generativeai as genai
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

genai.configure(api_key="AIzaSyDXpOrFSz_Lj18Hurh7ao7LZLE0_NwDyWo")

generation_config = {
    "temperature": 1,
    "top_p": 0.95,
    "top_k": 40,
    "max_output_tokens": 2048,
    "response_mime_type": "text/plain",
}

model = genai.GenerativeModel(
    model_name="gemini-2.0-flash-exp",
    generation_config=generation_config,
)

df = pd.read_csv('data_phong_tro_123.csv')
selected_columns = ['Price', 'Room Size', 'Address', 'Description', 'URL', 'Category']
history_data = df[selected_columns].to_dict(orient='records')

history_parts = []
for row in history_data:
    history_parts.append(f"Price: {row['Price']}, Room Size: {row['Room Size']}, Address: {row['Address']}, Description: {row['Description']}, URL: {row['URL']}, Category: {row['Category']}")

history = [
    {
        "role": "user",
        "parts": history_parts,  
    }
]


@app.route('/chat', methods=['POST'])
def chat():
    user_message = request.json.get("message")
    
    history.append({
        "role": "user",
        "parts": [user_message]
    })
    
    chat_session = model.start_chat(history=history)
    
    response = chat_session.send_message(user_message)
    
    history.append({
        "role": "model",
        "parts": [response.text]
    })
    
    return jsonify({"response": response.text, "history": history})

if __name__ == '__main__':
    app.run(debug=True)

from flask import Flask, request, jsonify

app = Flask(__name__)
conversion_rate = 0.012

@app.route('/convert', methods=['GET'])
def convert_currency():
    inr = request.args.get('inr')
    if not inr:
        return jsonify({"error": "INR amount missing"}), 400
    try:
        inr_value = float(inr)
        usd_value = inr_value * conversion_rate
        return jsonify({"INR": inr_value, "USD": round(usd_value, 2)})
    except ValueError:
        return jsonify({"error": "Invalid INR amount"}), 400

if __name__ == '__main__':
    app.run(debug=True)

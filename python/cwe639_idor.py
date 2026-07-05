from flask import Flask, jsonify

from models import Order  # SQLAlchemy model exposing a `.query` manager

app = Flask(__name__)


# CWE-639 — Authorization Bypass Through User-Controlled Key (IDOR).
# The user-controlled order id reaches a by-id lookup with no comparison to
# the authenticated principal (no ownership scoping).
@app.route("/orders/<order_id>")
def get_order(order_id):
    # SOURCE: user-controlled path parameter.
    # SINK (IDOR_LOOKUP): filter_by(id=...) with no ownership constraint.
    order = Order.query.filter_by(id=order_id).first()
    return jsonify(order.as_dict() if order else {})


if __name__ == "__main__":
    app.run(port=5001)

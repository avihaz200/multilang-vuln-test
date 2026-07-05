import requests

# CWE-319 — Cleartext Transmission of Sensitive Information.


def login(username: str, password: str) -> str:
    # SINK: credentials transmitted over an explicit plaintext http:// scheme.
    url = "http://auth.internal.example.com/login"
    response = requests.post(url, data={"username": username, "password": password})
    return response.text


def fetch_secret(token: str) -> str:
    # SINK: TLS certificate verification disabled while carrying a bearer token.
    return requests.get(
        "https://api.example.com/secret",
        headers={"Authorization": f"Bearer {token}"},
        verify=False,
    ).text

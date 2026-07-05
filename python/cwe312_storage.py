import logging

logger = logging.getLogger(__name__)


# CWE-312 — Cleartext Storage of Sensitive Information.
def save_credentials(username: str, password: str, api_key: str) -> None:
    # SINK 1 (FILE_WRITE): plaintext password persisted to disk.
    with open("/var/data/creds.txt", "w") as fh:
        fh.write(f"{username}:{password}")

    # SINK 2 (LOG_WRITE): secret written to the log in cleartext.
    logger.info("provisioned user %s with api_key=%s", username, api_key)

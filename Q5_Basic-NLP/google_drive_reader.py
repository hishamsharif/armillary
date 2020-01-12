import requests


def read_file_by_id(id):
    URL = "https://docs.google.com/uc?export=download"
    session = requests.Session()
    response = session.get(URL, params={'id': id}, stream=True)
    token = get_confirm_token(response)
    if token:
        params = {'id': id, 'confirm': token}
        response = session.get(URL, params=params, stream=True)
    return get_content(response)


def get_confirm_token(response):
    for key, value in response.cookies.items():
        if key.startswith('download_warning'):
            return value
    return None


def get_content(response):
    CHUNK_SIZE = 32768
    content = " ".join([x.decode('utf-8').strip() for x in response.iter_content(CHUNK_SIZE)])
    return content

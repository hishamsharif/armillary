import nltk
from nltk import pos_tag, ne_chunk
from nltk.tokenize import word_tokenize
from nltk.tree import Tree

import google_drive_reader


def recognize_named_entity(text):
    # tokenize the text
    word_tokens = word_tokenize(text)
    # part of speech tagging of words
    word_pos = pos_tag(word_tokens)
    # tree of word entities
    return ne_chunk(word_pos)


def format_recognized_entity(chunked):
    chunks = []
    # print chunked.flatten()
    entity_categories = {'PERSON': 'Person', 'ORGANIZATION': 'Organization', 'GPE': 'Location'}
    for chunk in chunked:
        if type(chunk) == Tree:
            if chunk.label() in entity_categories:
                category = entity_categories[chunk.label()]
                prefix = "<" + category + ">"
                suffix = "</" + category + ">"
            else:
                prefix = ""
                suffix = ""
            chunks.append(prefix + " ".join([token for token, pos in chunk.leaves()]) + suffix)
        else:
            chunks.append((chunk[0]))
    return " ".join(chunks)


def recognize_and_format(text):
    chunks = recognize_named_entity(text)
    res = format_recognized_entity(chunks)
    return res


def main(file_id):
    # read corpus news.txt from provided google drive storage
    text = google_drive_reader.read_file_by_id(file_id)
    # text = "Jim bought 300 shares of Acme Corp. in 2006"

    # parse the text, recognize & mark-up Person, Location, Organization in the text
    resutls = recognize_and_format(text)

    print resutls


if __name__ == "__main__":
    file_id = '1fLNB0b-zdzs2PkUIvTPI-uHkQBA5hQyZ'
    main(file_id)

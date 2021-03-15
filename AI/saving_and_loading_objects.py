import pickle


def save_object(_object, filename):
    with open(filename, "wb") as file:
        pickle.dump(_object, file)


def load_object(filename):
    with open(filename, "rb") as file:
        _object = pickle.load(file)
    return _object

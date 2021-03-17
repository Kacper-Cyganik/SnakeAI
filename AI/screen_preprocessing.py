import numpy as np
import matplotlib.pyplot as plt


# Constatnts
NUMBER_OF_BLOCKS = 20
BLOCK_SIZE = 16
COLOR_CHANNELS = 3
LOWER_HEIGHT_LIMIT = 56
UPPER_HEIGHT_LIMIT = LOWER_HEIGHT_LIMIT + NUMBER_OF_BLOCKS * BLOCK_SIZE
LOWER_WIDTH_LIMIT = 7
UPPER__WIDTH_LIMIT = LOWER_WIDTH_LIMIT + NUMBER_OF_BLOCKS * BLOCK_SIZE
MAX_PIXEL_VALUE = 255
# END Constatnts


def preprocess_screen(captured_screen):
    cutted_screen = cut_screen(captured_screen)
    shredded_screen = shred_screen(cutted_screen)
    gray_screen = gray_scale_screen(shredded_screen)
    normalized_screen = normalize_screen_to_range_0_1(gray_screen)
    return normalized_screen


def cut_screen(captured_screen):
    cutted_screen = captured_screen[LOWER_HEIGHT_LIMIT:UPPER_HEIGHT_LIMIT,
                                    LOWER_WIDTH_LIMIT:UPPER__WIDTH_LIMIT]
    return cutted_screen


def shred_screen(cutted_screen):
    grid_shape = (NUMBER_OF_BLOCKS, NUMBER_OF_BLOCKS, COLOR_CHANNELS)
    shredded_screen = np.zeros(grid_shape, dtype=cutted_screen.dtype)
    # i,j iterate thougth shredded_screen
    # x,y iterate thougth cutted_screen
    first_pixel = BLOCK_SIZE // 2
    x = first_pixel
    i = 0
    while x < NUMBER_OF_BLOCKS * BLOCK_SIZE:
        y = first_pixel
        j = 0
        while y < NUMBER_OF_BLOCKS * BLOCK_SIZE:
            shredded_screen[i][j] = cutted_screen[x][y]
            j += 1
            y += BLOCK_SIZE
        i += 1
        x += BLOCK_SIZE
    return shredded_screen


def gray_scale_screen(rgb_screen):
    red = rgb_screen[:, :, 0]
    green = rgb_screen[:, :, 1]
    blue = rgb_screen[:, :, 2]
    gray_screen = red*0.299 + green*0.587 + blue*0.114
    return gray_screen


def normalize_screen_to_range_0_1(screen):
    normalized_screnn = screen / MAX_PIXEL_VALUE
    return normalized_screnn

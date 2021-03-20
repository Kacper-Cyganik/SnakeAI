import win32gui
import matplotlib.pyplot as plt
import d3dshot

# globals
windows_list = []
d_shot = None


def initialize_capturing_window(screen_number=0):
    global d_shot
    if d_shot is None:
        d_shot = d3dshot.create(capture_output="numpy")
    d_shot.display = d_shot.displays[screen_number]


def capture_window(window_name):
    global d_shot
    window_position = get_window_position(window_name)
    screenshot = d_shot.screenshot(region=window_position)
    return screenshot


def get_window_position(window_name):
    window_handle = get_window_handle(window_name)
    window_position = win32gui.GetWindowRect(window_handle)
    return window_position


def get_window_handle(window_name):
    global windows_list
    window_name = window_name.lower()
    win32gui.EnumWindows(enum_windows, windows_list)
    windows = [(hwnd, title) for hwnd, title in windows_list if window_name == title.lower()]
    while len(windows) == 0:
        win32gui.EnumWindows(enum_windows, windows_list)
        windows = [(hwnd, title) for hwnd, title in windows_list if window_name == title.lower()]
    return windows[0][0]  # hard coded


def enum_windows(hwnd, results):
    global windows_list
    win_text = win32gui.GetWindowText(hwnd)
    windows_list.append((hwnd, win_text))

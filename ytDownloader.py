import os
import sys
from pytube import YouTube
from sys import argv
from pathlib import Path

i = 0
while i != -1:
    sentinelValue = input("Enter YouTube URL here:")
    if sentinelValue == -1:
        sys.exit(0)
    yt = YouTube(sentinelValue)

    print("Title: ", yt.title)

    print("Views: ", yt.views)

    yd = yt.streams.get_audio_only()

    download_folder = os.path.expanduser("~") + "\Music\YouTubeDownloads"

    yd.download(download_folder)
    i = sentinelValue
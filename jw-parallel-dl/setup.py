# opus_read -d JW300 -s af -t bg -wm moses -w jw300.af jw300.bg -dl ../corpus --suppress_prompts
import subprocess
import _thread
import os.path

code_list = [
    "ab",
    "abz",
    "ach",
    "af",
    "ami",
    "ann",
    "arn",
    "az",
    "ba",
    "ban",
    "bbc",
    "bg",
    "bhw",
    "bla",
    "btg",
    "btx",
    "cac",
    "car-x-crb",
    "cat",
    "ce",
    "ceb",
    "chk",
    "crs",
    "cs",
    "ctu",
    "cv",
    "cy",
    "da",
    "dag",
    "dak",
    "de",
    "dga",
    "dhv",
    "ee",
    "efi",
    "en",
    "es",
    "et",
    "eu",
    "ewo",
    "fa",
    "fi",
    "fj",
    "fmp",
    "fo",
    "fr",
    "fy",
    "ga",
    "gil",
    "gkn",
    "gu",
    "ha",
    "hil",
    "hop",
    "ht",
    "hu",
    "hz",
    "iba",
    "id",
    "idu",
    "ig",
    "ige",
    "is",
    "iso",
    "it",
    "ixl",
    "ja",
    "jv",
    "ka",
    "kab",
    "kei",
    "kg",
    "kk-cyrl",
    "kn",
    "ko",
    "krc",
    "ksf",
    "kss",
    "kwn",
    "ky",
    "lam",
    "ln",
    "lt",
    "lu",
    "lue",
    "lun",
    "luo",
    "lv",
    "mam",
    "mbc",
    "mcp",
    "men",
    "mfe",
    "mg",
    "mh",
    "mk",
    "ml",
    "mr",
    "mt",
    "mwv",
    "ng",
    "nia",
    "niu",
    "nl",
    "no",
    "nv",
    "ny",
    "pag",
    "pcm",
    "pl",
    "plg",
    "pmf",
    "ps",
    "pt",
    "quy",
    "quz",
    "ro",
    "rtm",
    "ru",
    "sg",
    "si",
    "sk",
    "sm",
    "sn",
    "so",
    "sq",
    "srm",
    "ss",
    "sv",
    "ta",
    "tar",
    "tdt",
    "te",
    "th",
    "tiv",
    "tk",
    "tl",
    "to",
    "tob",
    "toj",
    "tr",
    "ts",
    "tt",
    "tvl",
    "ty",
    "tzj",
    "tzo",
    "udm",
    "uk",
    "ur",
    "uz-Cyrl",
    "ve",
    "vi",
    "wba",
    "xh",
    "xpe",
    "yap",
    "yaq",
    "yo",
    "yrk",
    "yup",
    "zlm",
    "zu" ]

cmd="/home/l-acs/.local/bin/opus_read"
# cmd="opus_read"
dest="../corpus/"
pair_count=50000

# convert xml to plain text
for i in code_list:
    for j in code_list:
        if (i < j):
            filename=i + "-" + j + ".txt"
        else:
            filename=j + "-" + i + ".txt"

            
        if os.path.isfile(filename):
            
            print (filename + " already exists!")
            
        else:
            try:
                _thread.start_new_thread(
                    subprocess.run([cmd, "-d", "JW300",
                                    "-s", i,
                                    "-t", j,
                                    "--leave_non_alignments_out",
                                    "-m", pair_count,
                                    "--write", filename]))

            except:
                print ("Error: unable to start thread")


               
while 1:
    pass



                




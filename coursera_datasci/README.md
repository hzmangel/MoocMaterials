# Coursera Introduction to Data Science

This directory contains assignments and some other things for course *Introduction to Data Science*, the URL for this course is https://class.coursera.org/datasci-002 . Due to some personal issues, I haven't catch the time of course, so all the code in this directory is **NOT** verified by online judgement system.

## Env setup

Code is running and debugging on python2.7 (which is recommended by course introduction). The version I am using is 2.7.10 on OSX EI Capitan and I am not sure whether it still have the same action in different environment. The venv directory is excluded from git repo, so you may need to create your own virtual environment for Python.


Running this to create virtual environmnent:

```
virtualenv -p /usr/local/bin/python3 venv
```

And active venv Python in shell:

```
. ./venv/bin/activate
```

You can use `which python` and `python --version` command to verify the setup:

```bash
$ which python
/Users/vagrant/prjs/MoocMaterials/coursera_datasci/venv/bin/python
$ python --version
Python 2.7.10
```


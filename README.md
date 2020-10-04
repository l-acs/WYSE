# About

Project for MAIS Hacks October 2020. To provide machine translation to low-resource languages using a model trained on feature data and parallel JW300 corpora.

# Authors
Emi Baylor, Lucas Sahar, Elodie Ithier

# Technologies
Python (pytorch, pandas, opus_tools)
Clojure (cheshire, clj-http, ring, compojure, hiccup)

# Data
Feature vectors: https://sigtyp.github.io/st2020.html
Parallel corpora: http://opus.nlpl.eu/JW300.php

# Usage
To run the webapp locally, navigate to `mais-compoj/` and run:
`lein ring server`
(to open on http://localhost:3000 by default).


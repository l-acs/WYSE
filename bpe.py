# -*- coding: utf-8 -*-
"""
Created on Sat Oct  3 20:13:39 2020

@author: Admin
"""
import pandas as pd
import collections
import re




def bpe(dataframes):
    corpus=[]
    tokens=[]
    
    #for each dataframe change to list and split each value
    for df in dataframes:
        tokens.append(df.values.tolist().split(" "))
        
    for token in tokens:
        corpus.append(token)
            
    #keep distinct words from the list        
    words = list(set(" ".join(corpus)))
    words.remove(' ')
    
    # step 2: seperate word into its chars 
    for token in corpus:
        corpus.append(" ".join(token))
   
    
    #step 3: add </w> to dinstinguish end of words
    for token in corpus:
        corpus.append(token + '/w')
  
    
    #step 4: create a dictionary with frequency of word
    corpus = collections.Counter(corpus)
    corpus = dict(corpus)
    #print("Corpus:",corpus)
    
    #step 5: collect frequency of characters and sequences
    def compute_freq(corpus):
        """calculates frequency of sequence of characters
        arguments: corpus
        output: frequency of each pair of characters"""
        pairs = collections.defaultdict(int)
        for word, frequency in corpus.items():
            symbols = word.split()
            for i in range(0,len(symbols)-1):
                pairs[symbols[i],symbols[i+1]] += frequency
        return pairs
    
    
    #step 6: copied off the net (to be tested)
    def merge_vocab(pair, corpus):
        """merge the most frequent pair in the corpus. 
        aruments: original corpus, best pair
        returns: modified corpus"""
        output = {}
        bigram = re.escape(' '.join(pair))
        p = re.compile(r'(?<!\S)' + bigram + r'(?!\S)')
        
        for word in corpus:
            w_out = p.sub(''.join(pair), word)
            output[w_out] = corpus[word]
        
        return output
    
    #step 7: generate a dict with frequency of chars
    pairs = compute_freq(corpus)
    #print(pairs)
    
    #step 8: calculate the best pair
    highest = max(pairs, key=pairs.get)
    
    
    #merge the frequent pair in corpus
    #also copied off the net
    corpus = merge_vocab(highest, corpus)
    
    #convert a tuple to a string
    highest = "".join(list(highest))
    
    #append to merge list and vocabulary
    merges = []
    merges.append(highest)
    words.append(highest)
    
    # everything below was code found online
    num_merges = 10
    for i in range(num_merges):
        
        #compute frequency of bigrams in a corpus
        pairs = compute_freq(corpus)
        
        #compute the best pair
        best = max(pairs, key=pairs.get)
        
        #merge the frequent pair in corpus
        corpus = merge_vocab(best, corpus)
        
        #append to merge list and vocabulary
        merges.append(best)
        words.append(best)
    
    #convert a tuple to a string
    merges_in_string = ["".join(list(i)) for i in merges]
    print("BPE Merge Operations:",merges_in_string)
    
    
    

    #how it is applied given a word example: "tallest"
    # def bpe_logic(oov):
    #     i=0
    #     while(True):
        
    #         
    #         pairs = compute_freq(oov)
        
    #         
    #         pairs = pairs.keys()
            
    #         #find the pairs available in the learned operations
    #         ind=[merges.index(i) for i in pairs if i in merges]
        
    #         if(len(ind)==0):
    #             print("\nBPE Completed...")
    #             break
            
    #         #choose the most frequent learned operation
    #         best = merges[min(ind)]
            
    #         #merge the best pair
    #         oov = merge_vocab(best, oov)
            
    #         print("Iteration ",i+1, list(oov.keys())[0])
    #         i=i+1
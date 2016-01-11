import sys
import json


def build_sentiment_score_dict(sentiment_score_file):
    scores = {}
    for l in sentiment_score_file:
        term, score = l.split("\t")
        scores[term] = int(score)

    return scores


def calc_sent_time(sentiment_dict, tweet_fp):
    for l in tweet_fp:
        tweet_dict = json.loads(str(l))
        text = tweet_dict.get('text')

        if text is None:
            continue

        tweet_score = sum([sentiment_dict.get(word, 0) for word in text.split(' ')])
        print(tweet_score)


def lines(fp):
    print(len(fp.readlines()))


def main():
    sent_file = open(sys.argv[1])
    tweet_file = open(sys.argv[2])

    pre_calculated_sentiment_scores = build_sentiment_score_dict(sent_file)
    calc_sent_time(pre_calculated_sentiment_scores, tweet_file)

    lines(sent_file)
    lines(tweet_file)

if __name__ == '__main__':
    main()

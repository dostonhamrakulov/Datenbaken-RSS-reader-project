import json


myjson = """[
    {
        "id": 606,
        "title": "Tech Giants Amass a Lobbying Army for an Epic Washington Battle",
        "link": "https://www.nytimes.com/2019/06/05/us/politics/amazon-apple-facebook-google-lobbying.html?emc=rss&partner=rss",
        "description": "Amazon, Apple, Facebook and Google, facing the growing possibility of antitrust action and legislation to rein in their power, are spending freely to gain influence and access.",
        "published_date": "Thu, 06 Jun 2019 01:04:39 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 607,
        "title": "Democratic Candidates Woo Silicon Valley for Donations, Then Bash It",
        "link": "https://www.nytimes.com/2019/06/06/us/politics/democrats-2020-donations-silicon-valley.html?emc=rss&partner=rss",
        "description": "Until recently big tech companies were seen as one of the few relatively untainted sources of big-money donations for Democrats. Now, that’s changing.",
        "published_date": "Fri, 07 Jun 2019 04:00:48 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 608,
        "title": "Election Rules Are an Obstacle to Cybersecurity of Presidential Campaigns",
        "link": "https://www.nytimes.com/2019/06/06/technology/ftc-rules-cyberattacks.html?emc=rss&partner=rss",
        "description": "Security experts warn that time is running out for campaigns to create protections against the cyberattacks and disinformation seen in recent elections.",
        "published_date": "Thu, 06 Jun 2019 22:41:27 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 609,
        "title": "In China, a Viral Video Sets Off a Challenge to Rape Culture",
        "link": "https://www.nytimes.com/2019/06/05/business/china-richard-liu-rape-video-metoo.html?emc=rss&partner=rss",
        "description": "The images were meant to exonerate Richard Liu, the e-commerce mogul. They have also helped fuel a nascent #NoPerfectVictim movement.",
        "published_date": "Fri, 07 Jun 2019 17:00:54 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 610,
        "title": "FedEx Says It’s Ending Express Shipping Service for Amazon",
        "link": "https://www.nytimes.com/2019/06/07/business/fedex-amazon-express-delivery.html?emc=rss&partner=rss",
        "description": "The decision, while not financially significant for either company, shows how the online retailer has gone from a sought-after customer to a direct competitor of FedEx.",
        "published_date": "Sat, 08 Jun 2019 00:25:42 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 611,
        "title": "Two Top Uber Executives Are Out as C.E.O. Consolidates Power",
        "link": "https://www.nytimes.com/2019/06/07/technology/uber-chief-operating-marketing-officer.html?emc=rss&partner=rss",
        "description": "A month after Uber’s rocky I.P.O., its chief executive laid off two members of his executive team: the chief operating officer and the chief marketing officer.",
        "published_date": "Sat, 08 Jun 2019 01:49:53 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 612,
        "title": "Want to Buy a Ticket to the Space Station? NASA Says Soon You Can",
        "link": "https://www.nytimes.com/2019/06/07/science/space-station-nasa.html?emc=rss&partner=rss",
        "description": "NASA plans to open the International Space Station to commercial business, including tourism. But the tickets won’t be cheap.",
        "published_date": "Sat, 08 Jun 2019 05:54:18 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 613,
        "title": "The Week in Tech: What Not to Expect From Big Tech’s Antitrust Showdown",
        "link": "https://www.nytimes.com/2019/06/07/technology/big-tech-antitrust.html?emc=rss&partner=rss",
        "description": "The legal action against Silicon Valley’s giants will be long, difficult, uncertain and, for some people, disappointing.",
        "published_date": "Fri, 07 Jun 2019 15:00:01 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 614,
        "title": "Encountering the ‘New Order’ at MoMA",
        "link": "https://www.nytimes.com/2019/06/06/arts/design/museum-of-modern-art-technology.html?emc=rss&partner=rss",
        "description": "The blind spots and new possibilities of the technologies we love (and hate).",
        "published_date": "Fri, 07 Jun 2019 08:22:40 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 615,
        "title": "Google to Buy Data Analytics Company Despite New Antitrust Scrutiny",
        "link": "https://www.nytimes.com/2019/06/06/technology/google-data-analytics-looker-acquisition.html?emc=rss&partner=rss",
        "description": "Google said it would acquire Looker for $2.6 billion in a bid to catch rivals in the cloud-computing industry.",
        "published_date": "Thu, 06 Jun 2019 20:05:59 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 616,
        "title": "Uber Copter to Offer Flights From Lower Manhattan to J.F.K.",
        "link": "https://www.nytimes.com/2019/06/05/travel/uber-helicopter-nyc-jfk.html?emc=rss&partner=rss",
        "description": "For an 8-minute, one-way flight, the average ride will cost between $200 and $225 per person and include private ground transportation on both ends of the trip.",
        "published_date": "Thu, 06 Jun 2019 00:31:56 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 617,
        "title": "Overlooked No More: Alan Turing, Condemned Code Breaker and Computer Visionary",
        "link": "https://www.nytimes.com/2019/06/05/obituaries/alan-turing-overlooked.html?emc=rss&partner=rss",
        "description": "His ideas led to early versions of modern computing and helped win World War II. Yet he died as a criminal for his homosexuality.",
        "published_date": "Fri, 07 Jun 2019 01:11:16 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 618,
        "title": "YouTube to Remove Thousands of Videos Pushing Extreme Views",
        "link": "https://www.nytimes.com/2019/06/05/business/youtube-remove-extremist-videos.html?emc=rss&partner=rss",
        "description": "The decision by YouTube, which is owned by Google, is the latest action by a Silicon Valley company to stem the spread of hate speech and disinformation on their sites.",
        "published_date": "Wed, 05 Jun 2019 22:26:32 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 619,
        "title": "Your Instagram Feed Is About to Have More Ads From Influencers",
        "link": "https://www.nytimes.com/2019/06/04/technology/instagram-ads-influencers.html?emc=rss&partner=rss",
        "description": "On Tuesday, the photo platform announced that advertisers could promote influencer-created content in people’s feeds, even if those people do not follow the influencer.",
        "published_date": "Wed, 05 Jun 2019 05:17:28 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 620,
        "title": "In China, a Reuters Partner Blocks Articles on the Tiananmen Square Massacre",
        "link": "https://www.nytimes.com/2019/06/04/business/media/china-tiananmen-square-reuters-censored.html?emc=rss&partner=rss",
        "description": "The 30th anniversary of the pro-democracy protests has prompted a censorship wave in China. Refinitiv, a data company partly owned by Reuters, complied.",
        "published_date": "Wed, 05 Jun 2019 06:49:32 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 621,
        "title": "Vice Media Parts With 2 Top Editors, After Layoffs and Before an Expansion",
        "link": "https://www.nytimes.com/2019/06/04/business/media/vice-shakeup-editors-exit.html?emc=rss&partner=rss",
        "description": "Under the company’s new digital leader, Katie Drummond, Vice.com’s editor in chief and managing editor are leaving, with Erika Allen, formerly of Vice Magazine, arriving to replace them.",
        "published_date": "Tue, 04 Jun 2019 23:13:26 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 622,
        "title": "Come for the Deep-Sea Selfies. Stay to Learn About Sustainability.",
        "link": "https://www.nytimes.com/2019/06/04/arts/design/ocean-cube-pop-up-new-york.html?emc=rss&partner=rss",
        "description": "“Ocean Cube,” an exhibition of five Instagramable dreamlike rooms, offers commentary on pollution.",
        "published_date": "Wed, 05 Jun 2019 06:12:02 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 623,
        "title": "Apple Backs Off Crackdown on Parental-Control Apps",
        "link": "https://www.nytimes.com/2019/06/03/technology/apple-parental-control-apps.html?emc=rss&partner=rss",
        "description": "The iPhone maker acted as tech giants faced more scrutiny over what critics argue is anticompetitive behavior.",
        "published_date": "Tue, 04 Jun 2019 08:54:04 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 624,
        "title": "A Farewell for iTunes",
        "link": "https://www.nytimes.com/2019/06/03/technology/itunes-shutting-down.html?emc=rss&partner=rss",
        "description": "Apple’s software changed the way we listen to music. And though it got a little bloated over the years, we will miss it.",
        "published_date": "Tue, 04 Jun 2019 01:42:41 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 625,
        "title": "Elizabeth Warren Sticks Her Message in Big Tech’s Face",
        "link": "https://www.nytimes.com/2019/06/03/technology/elizabeth-warren-big-tech-break-up.html?emc=rss&partner=rss",
        "description": "The “Break Up Big Tech” billboard, paid for by the Democrat’s presidential campaign, doesn’t sit well with all San Franciscans who work in the industry.",
        "published_date": "Mon, 03 Jun 2019 22:35:48 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 626,
        "title": "Antitrust Troubles Snowball for Tech Giants as Lawmakers Join In",
        "link": "https://www.nytimes.com/2019/06/03/technology/facebook-ftc-antitrust.html?emc=rss&partner=rss",
        "description": "Intense new scrutiny on companies like Google, Apple, Facebook and Amazon could last years and lead to the first overhaul of antitrust rules in decades.",
        "published_date": "Tue, 04 Jun 2019 04:21:34 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 627,
        "title": "Apple’s WWDC Highlights: the Death of iTunes and $6,000 Macs",
        "link": "https://www.nytimes.com/2019/06/03/technology/wwdc-apple.html?emc=rss&partner=rss",
        "description": "Apple rolled out ambitious updates to its apps and smartwatch at its annual developer conference.",
        "published_date": "Fri, 07 Jun 2019 17:00:54 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 628,
        "title": "On YouTube’s Digital Playground, an Open Gate for Pedophiles",
        "link": "https://www.nytimes.com/2019/06/03/world/americas/youtube-pedophiles.html?emc=rss&partner=rss",
        "description": "The site’s automated recommendation system, at times drawing on home movies of unwitting families, created a vast video catalog of prepubescent children.",
        "published_date": "Tue, 04 Jun 2019 20:29:01 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 629,
        "title": "Google Disruptions Affect Gmail, YouTube and Other Sites",
        "link": "https://www.nytimes.com/2019/06/02/technology/google-gmail-snapchat-outage.html?emc=rss&partner=rss",
        "description": "The errors also affected businesses that rely on Google’s technology. Google said the problems were not caused by a cyberattack.",
        "published_date": "Mon, 03 Jun 2019 03:34:56 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 630,
        "title": "As Slack Prepares to Go Public, Its C.E.O. Is Holding His Tongue",
        "link": "https://www.nytimes.com/2019/06/02/technology/slack-stewart-butterfield.html?emc=rss&partner=rss",
        "description": "Stewart Butterfield, Slack’s chief executive, has been outspoken. Maybe too outspoken for a business software company that is about to go public.",
        "published_date": "Sun, 02 Jun 2019 23:57:38 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    },
    {
        "id": 631,
        "title": "An HBO Question Is Giving AT&T Executives a Headache",
        "link": "https://www.nytimes.com/2019/06/02/business/media/att-warner-hbo-streaming.html?emc=rss&partner=rss",
        "description": "The corporate home of Wonder Woman, Harry Potter and “Friends” weighs how its streaming service can compete on price with Disney, Netflix and Amazon.",
        "published_date": "Mon, 03 Jun 2019 02:03:36 CEST",
        "imported_date": "Sat, 08 Jun 2019 12:18:06 CEST",
        "provider_id": 13,
        "image": "src_img"
    }
]"""

my_dict = json.load(myjson)

d = collections.defaultdict(list)
for k, v in my_dict:
    if v not in d[k]: # if value not in list already
        d[k].append(v)


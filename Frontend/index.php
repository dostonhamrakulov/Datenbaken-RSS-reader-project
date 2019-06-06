<?php include ('header.php');?>
<div class="container">
<h2>Welcome To RSS Fetcher </h2>
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="ENTER THE RSS LINK HERE!!">
                <span class="input-group-btn">
        <button class="btn btn-default" type="button">Go!</button>
      </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
    </div>
    <div class="row">
        <?php
        $rss = new DOMDocument();
        $rss->load('http://feeds.bbci.co.uk/news/world/europe/rss.xml');
        $feed = array();
        foreach ($rss->getElementsByTagName('item') as $node) {
            $item = array (
                'title' => $node->getElementsByTagName('title')->item(0)->nodeValue,
                'desc' => $node->getElementsByTagName('description')->item(0)->nodeValue,
                'link' => $node->getElementsByTagName('link')->item(0)->nodeValue,
                'date' => $node->getElementsByTagName('pubDate')->item(0)->nodeValue,
            );
            array_push($feed, $item);
        }
        $limit = 5;
        for($x=0;$x<$limit;$x++) {
            $title = str_replace(' & ', ' &amp; ', $feed[$x]['title']);
            $link = $feed[$x]['link'];
            $description = $feed[$x]['desc'];
            $date = date('l F d, Y', strtotime($feed[$x]['date']));
            echo '<p><strong><a href="'.$link.'" title="'.$title.'">'.$title.'</a></strong><br />';
            echo '<small><em>Posted on '.$date.'</em></small></p>';
            echo '<p>'.$description.'</p>';
        }
        ?>

    </div>
</div>

<?php include ('footer.php');?>

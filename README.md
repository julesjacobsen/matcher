ECHo
--
This is a simple Spring boot application to demonstrate a peer-to-peer network where a REST request sent to one server will 
forward on that request to all of its peers and collate back all of their responses.

It's totally naive, will match against itself, has no security, no external node discovery, no failure tolerance, no tests
 and is generally pointless.
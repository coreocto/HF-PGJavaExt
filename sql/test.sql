select H(encode('hello','base64'),encode('world','base64')); --return '/F4DjTilcDIIVEHn/nAQsA=='

select 1 where H(null,encode('world','base64')) is not null;

select 1 where H(encode('hello','base64'),null) is not null;
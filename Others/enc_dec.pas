function AcaX(s: string; key: string): string; export;
var
  i, j: integer;
  k: array[1..3] of byte;
begin
	if (length(key) >= 8) then begin
	  k[1] := StrToInt('$'+key[2]);
	  k[2] := StrToInt('$'+key[4]);
	  k[3] := StrToInt('$'+key[5]);
	  i := 1;
	  for j := 1 to length(s) do begin
		s[j] := chr( ord(s[j]) xor k[i] );
		if (i<3) then
		  i := i + 1
		else
		  i := 1;
	  end;
	  Result := s;
	end
	else
	  Result := '';
end;
